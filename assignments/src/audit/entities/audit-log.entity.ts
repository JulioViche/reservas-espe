import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

export enum AuditAction {
  CREATION = 'CREATION',
  MODIFICATION = 'MODIFICATION',
  ELIMINATION = 'ELIMINATION',
}

@Entity('audit_logs')
export class AuditLog {
  @PrimaryGeneratedColumn('uuid')
  id: string;

  @Column('uuid')
  userId: string;

  @Column('uuid')
  vehicleId: string;

  @Column({ type: 'enum', enum: AuditAction })
  action: AuditAction;

  @Column({ type: 'timestamp with time zone', default: () => 'NOW()' })
  timestamp: Date;

  @Column({ type: 'jsonb', nullable: true })
  previousState: Record<string, unknown> | null;

  @Column({ type: 'jsonb', nullable: true })
  newState: Record<string, unknown> | null;
}
