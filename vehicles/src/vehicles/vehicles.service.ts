import { Injectable } from '@nestjs/common';
import { CreateVehicleDto } from './dto/create-vehicle.dto';
import { UpdateVehicleDto } from './dto/update-vehicle.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Vehicle } from './entities/vehicle.entity';
import { Repository } from 'typeorm';
import { VehicleFactory } from './factories/vehicle.factory';

@Injectable()
export class VehiclesService {
  constructor(
    @InjectRepository(Vehicle) private vehicleRepository: Repository<Vehicle>,
  ) {}

  async create(createVehicleDto: CreateVehicleDto) {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { plate: createVehicleDto.data.plate },
    });

    if (existingVehicle)
      throw new Error('A vehicle with this plate already exists');

    const newVehicle = VehicleFactory.create(createVehicleDto);
    return this.vehicleRepository.save(newVehicle);
  }

  async findAll() {
    return await this.vehicleRepository.find();
  }

  async findOne(id: string) {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { id },
    });

    if (!existingVehicle) throw new Error('Vehicle not found');

    return existingVehicle;
  }

  async update(id: string, updateVehicleDto: UpdateVehicleDto) {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { id },
    });

    if (!existingVehicle) throw new Error('Vehicle not found');

    const updatedVehicle = Object.assign(existingVehicle, updateVehicleDto);
    return this.vehicleRepository.save(updatedVehicle);
  }

  async remove(id: string) {
    const existingVehicle = await this.vehicleRepository.findOne({
      where: { id },
    });

    if (!existingVehicle) throw new Error('Vehicle not found');
  }
}
