import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';
import { ApiProperty } from '@nestjs/swagger';

export enum AuditAction {
  CREATION = 'CREATION',
  MODIFICATION = 'MODIFICATION',
  ELIMINATION = 'ELIMINATION',
}

@Entity('audit_logs')
export class AuditLog {
  @PrimaryGeneratedColumn('uuid')
  @ApiProperty({ description: 'Identificador único del registro de auditoría' })
  id: string;

  @Column('uuid')
  @ApiProperty({ description: 'Identificador del propietario', example: '123e4567-e89b-12d3-a456-426614174000' })
  userId: string;

  @Column('uuid')
  @ApiProperty({ description: 'Identificador del vehículo', example: '123e4567-e89b-12d3-a456-426614174001' })
  vehicleId: string;

  @Column({ type: 'enum', enum: AuditAction })
  @ApiProperty({ description: 'Acción realizada', enum: AuditAction, example: AuditAction.MODIFICATION })
  action: AuditAction;

  @Column({ type: 'timestamp with time zone', default: () => 'NOW()' })
  @ApiProperty({ description: 'Fecha y hora del cambio' })
  timestamp: Date;

  @Column({ type: 'jsonb', nullable: true })
  @ApiProperty({ description: 'Estado anterior de la asignación', nullable: true })
  previousState: Record<string, unknown> | null;

  @Column({ type: 'jsonb', nullable: true })
  @ApiProperty({ description: 'Nuevo estado de la asignación', nullable: true })
  newState: Record<string, unknown> | null;
}
