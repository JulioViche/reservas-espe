import { CreateVehicleDto } from '../dto/create-vehicle.dto';
import { Car } from '../entities/car.entity';
import { Motorcycle } from '../entities/motorcycle.entity';
import { Truck } from '../entities/truck.entity';
import { Vehicle } from '../entities/vehicle.entity';

export class VehicleFactory {
  static create(dto: CreateVehicleDto): Vehicle {
    switch (dto.type) {
      case 'car':
        const car = new Car();
        Object.assign(car, dto.data);
        return car;
      case 'motorcycle':
        const motorcycle = new Motorcycle();
        Object.assign(motorcycle, dto.data);
        return motorcycle;
      case 'truck':
        const truck = new Truck();
        Object.assign(truck, dto.data);
        return truck;
      default:
        throw new Error(`Vehicle type not supported: ${dto.type}`);
    }
  }
}
