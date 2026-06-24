import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { AuditLog, AuditAction } from './entities/audit-log.entity';

@Injectable()
export class AuditService {
  constructor(
    @InjectRepository(AuditLog)
    private readonly auditLogRepository: Repository<AuditLog>,
  ) {}

  async logModification(
    userId: string,
    vehicleId: string,
    previousState: Record<string, unknown> | null,
    newState: Record<string, unknown> | null,
  ): Promise<void> {
    const log = this.auditLogRepository.create({
      userId,
      vehicleId,
      action: AuditAction.MODIFICATION,
      timestamp: new Date(),
      previousState,
      newState,
    });
    await this.auditLogRepository.save(log);
  }

  async findByAssignment(
    userId: string,
    vehicleId: string,
  ): Promise<AuditLog[]> {
    return this.auditLogRepository.find({
      where: { userId, vehicleId },
      order: { timestamp: 'DESC' },
    });
  }

  async findByUser(userId: string): Promise<AuditLog[]> {
    return this.auditLogRepository.find({
      where: { userId },
      order: { timestamp: 'DESC' },
    });
  }
}
