import { ChildEntity, Column } from 'typeorm';
import { Vehicle } from './vehicle.entity';

@ChildEntity('truck')
export class Truck extends Vehicle {
  @Column()
  cargoCapacity!: number;

  getType(): string {
    return 'truck';
  }
}
