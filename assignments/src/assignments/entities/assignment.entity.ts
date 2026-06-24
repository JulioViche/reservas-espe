import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity('assignments')
export class Assignment {
  @PrimaryColumn('uuid')
  userId: string;

  @PrimaryColumn('uuid')
  vehicleId: string;

  @Column({ type: 'timestamp with time zone', default: () => 'NOW()' })
  assignedAt: Date;

  @Column({ type: 'timestamp with time zone', nullable: true })
  unassignedAt: Date | null;

  @Column({ default: true })
  isActive: boolean;
}
