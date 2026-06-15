import { ChildEntity, Column } from 'typeorm';
import { Vehicle } from './vehicle.entity';

export enum MotorcycleType {
  SPORT = 'sport',
  MOTOCROSS = 'motocross',
  SCOOTER = 'scooter',
}

@ChildEntity()
export class Motorcycle extends Vehicle {
  @Column({ type: 'enum', enum: MotorcycleType })
  type!: MotorcycleType;

  getType(): string {
    return 'motorcycle';
  }
}
