import { ChildEntity, Column } from 'typeorm';
import { Vehicle } from './vehicle.entity';

@ChildEntity('car')
export class Car extends Vehicle {
  @Column()
  doors!: number;

  @Column()
  trunkCapacity!: number;

  getType(): string {
    return 'car';
  }
}
