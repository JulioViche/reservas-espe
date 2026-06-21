import {
  Injectable,
  NotFoundException,
  ConflictException,
} from '@nestjs/common';
import { CreateVehicleDto } from './dto/create-vehicle.dto';
import { UpdateVehicleDto } from './dto/update-vehicle.dto';
import { ResponseVehicleDto } from './dto/response-vehicle.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Vehicle } from './entities/vehicle.entity';
import { Repository } from 'typeorm';
import { VehicleFactory } from './factories/vehicle.factory';
import { plainToInstance } from 'class-transformer';
import { Car } from './entities/car.entity';
import { Motorcycle } from './entities/motorcycle.entity';
import { Truck } from './entities/truck.entity';

@Injectable()
export class VehiclesService {
  constructor(
    @InjectRepository(Vehicle) private vehicleRepository: Repository<Vehicle>,
  ) {}

  async create(
    createVehicleDto: CreateVehicleDto,
  ): Promise<ResponseVehicleDto> {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { plate: createVehicleDto.data.plate },
    });

    if (existingVehicle)
      throw new ConflictException('A vehicle with this plate already exists');

    const newVehicle = VehicleFactory.create(createVehicleDto);
    const saved = await this.vehicleRepository.save(newVehicle);
    return this.toResponseDto(saved);
  }

  async findAll(): Promise<ResponseVehicleDto[]> {
    const vehicles = await this.vehicleRepository.find();
    return vehicles.map((v) => this.toResponseDto(v));
  }

  async findOne(id: string): Promise<ResponseVehicleDto> {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { id },
    });

    if (!existingVehicle) throw new NotFoundException('Vehicle not found');

    return this.toResponseDto(existingVehicle);
  }

  async update(
    id: string,
    updateVehicleDto: UpdateVehicleDto,
  ): Promise<ResponseVehicleDto> {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { id },
    });

    if (!existingVehicle) throw new NotFoundException('Vehicle not found');

    const updatedVehicle = Object.assign(existingVehicle, updateVehicleDto);
    const saved = await this.vehicleRepository.save(updatedVehicle);
    return this.toResponseDto(saved);
  }

  async remove(id: string): Promise<void> {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { id },
    });

    if (!existingVehicle) throw new NotFoundException('Vehicle not found');

    await this.vehicleRepository.softRemove(existingVehicle);
  }

  private toResponseDto(vehicle: Vehicle): ResponseVehicleDto {
    const base = {
      id: vehicle.id,
      plate: vehicle.plate,
      brand: vehicle.brand,
      model: vehicle.model,
      color: vehicle.color,
      year: vehicle.year,
      classification: vehicle.classification,
      type: vehicle.getType(),
    };

    if (vehicle instanceof Car) {
      return plainToInstance(ResponseVehicleDto, {
        ...base,
        doors: vehicle.doors,
        trunkCapacity: vehicle.trunkCapacity,
      });
    }

    if (vehicle instanceof Motorcycle) {
      return plainToInstance(ResponseVehicleDto, {
        ...base,
        motorcycleType: vehicle.type,
      });
    }

    if (vehicle instanceof Truck) {
      return plainToInstance(ResponseVehicleDto, {
        ...base,
        cargoCapacity: vehicle.cargoCapacity,
      });
    }

    return plainToInstance(ResponseVehicleDto, base);
  }
}
