import {
  Column,
  DeleteDateColumn,
  Entity,
  PrimaryGeneratedColumn,
  TableInheritance,
} from 'typeorm';

export enum Classification {
  ELECTRIC = 'electric',
  HYBRID = 'hybrid',
  GASOLINE = 'gasoline',
  DIESEL = 'diesel',
}

@Entity()
@TableInheritance({ column: { type: 'varchar', name: 'type' } })
export abstract class Vehicle {
  @PrimaryGeneratedColumn('uuid')
  id!: string;

  @Column({ unique: true })
  plate!: string;

  @Column()
  brand!: string;

  @Column()
  model!: string;

  @Column()
  year!: number;

  @Column()
  color!: string;

  @Column({ type: 'enum', enum: Classification })
  classification!: Classification;

  @DeleteDateColumn()
  deletedAt?: Date;

  abstract getType(): string;
}
