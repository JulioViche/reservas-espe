import { Column, Entity, PrimaryColumn } from 'typeorm';
import { ApiProperty } from '@nestjs/swagger';

@Entity('assignments')
export class Assignment {
  @PrimaryColumn('uuid')
  @ApiProperty({ description: 'Identificador del propietario', example: '123e4567-e89b-12d3-a456-426614174000' })
  userId: string;

  @PrimaryColumn('uuid')
  @ApiProperty({ description: 'Identificador del vehículo', example: '123e4567-e89b-12d3-a456-426614174001' })
  vehicleId: string;

  @Column({ type: 'timestamp with time zone', default: () => 'NOW()' })
  @ApiProperty({ description: 'Fecha y hora de asignación' })
  assignedAt: Date;

  @Column({ type: 'timestamp with time zone', nullable: true })
  @ApiProperty({ description: 'Fecha y hora de desasignación', nullable: true })
  unassignedAt: Date | null;

  @Column({ default: true })
  @ApiProperty({ description: 'Indica si la asignación está activa', example: true })
  isActive: boolean;
}
