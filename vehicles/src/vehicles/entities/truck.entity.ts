import { ChildEntity, Column } from 'typeorm';
import { Vehicle } from './vehicle.entity';

@ChildEntity()
export class Truck extends Vehicle {
  @Column()
  cargoCapacity!: number;

  getType(): string {
    return 'truck';
  }
}
