import {
  Injectable,
  ConflictException,
  NotFoundException,
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Assignment } from './entities/assignment.entity';
import { CreateAssignmentDto } from './dto/create-assignment.dto';
import { AuditService } from '../audit/audit.service';
import { VehiclesClientService } from '../common/clients/vehicles-client.service';

@Injectable()
export class AssignmentsService {
  constructor(
    @InjectRepository(Assignment)
    private readonly assignmentRepository: Repository<Assignment>,
    private readonly auditService: AuditService,
    private readonly vehiclesClient: VehiclesClientService,
  ) {}

  async create(dto: CreateAssignmentDto): Promise<Assignment> {
    const existing = await this.assignmentRepository.findOne({
      where: { vehicleId: dto.vehicleId, isActive: true },
    });

    if (existing) {
      throw new ConflictException('Vehicle is already assigned to an owner');
    }

    const assignment = this.assignmentRepository.create({
      userId: dto.userId,
      vehicleId: dto.vehicleId,
      assignedAt: new Date(),
      isActive: true,
    });

    return this.assignmentRepository.save(assignment);
  }

  async remove(userId: string, vehicleId: string): Promise<void> {
    const assignment = await this.assignmentRepository.findOne({
      where: { userId, vehicleId, isActive: true },
    });

    if (!assignment) {
      throw new NotFoundException('Active assignment not found');
    }

    assignment.isActive = false;
    assignment.unassignedAt = new Date();
    await this.assignmentRepository.save(assignment);
  }

  async findByOwner(userId: string): Promise<Assignment[]> {
    return this.assignmentRepository.find({
      where: { userId, isActive: true },
    });
  }

  async getFleetByOwner(userId: string) {
    const assignments = await this.findByOwner(userId);

    const vehicles = await Promise.all(
      assignments.map(async (a) => {
        const v = await this.vehiclesClient.getVehicle(a.vehicleId);
        return {
          id: v.id,
          plate: v.plate,
          brand: v.brand,
          model: v.model,
          type: v.type,
          category: v.classification,
        };
      }),
    );

    return { userId, vehicles };
  }

  async update(
    userId: string,
    vehicleId: string,
    updates: Partial<Pick<Assignment, 'isActive'>>,
  ): Promise<Assignment> {
    const assignment = await this.assignmentRepository.findOne({
      where: { userId, vehicleId },
    });

    if (!assignment) {
      throw new NotFoundException('Assignment not found');
    }

    const previousState = { ...assignment } as unknown as Record<
      string,
      unknown
    >;

    Object.assign(assignment, updates);

    if (updates.isActive === false) {
      assignment.unassignedAt = new Date();
    }

    const saved = await this.assignmentRepository.save(assignment);

    await this.auditService.logModification(
      userId,
      vehicleId,
      previousState,
      saved as unknown as Record<string, unknown>,
    );

    return saved;
  }
}
