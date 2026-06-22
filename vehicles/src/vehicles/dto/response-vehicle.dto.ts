import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';

export class ResponseVehicleDto {
  @ApiProperty({ description: 'Identificador único del vehículo', example: '123e4567-e89b-12d3-a456-426614174000' })
  id!: string;

  @ApiProperty({ description: 'Placa del vehículo', example: 'ABC-1234' })
  plate!: string;

  @ApiProperty({ description: 'Marca del vehículo', example: 'Toyota' })
  brand!: string;

  @ApiProperty({ description: 'Modelo del vehículo', example: 'Corolla' })
  model!: string;

  @ApiProperty({ description: 'Color del vehículo', example: 'Rojo' })
  color!: string;

  @ApiProperty({ description: 'Año del vehículo', example: 2024 })
  year!: number;

  @ApiProperty({ description: 'Clasificación del vehículo', example: 'gasoline' })
  classification!: string;

  @ApiProperty({ description: 'Tipo de vehículo', example: 'car' })
  type!: string;

  @ApiPropertyOptional({ description: 'Número de puertas (automóvil)', example: 4 })
  doors?: number;

  @ApiPropertyOptional({ description: 'Capacidad del maletero en litros (automóvil)', example: 500 })
  trunkCapacity?: number;

  @ApiPropertyOptional({ description: 'Tipo de motocicleta', example: 'sport' })
  motorcycleType?: string;

  @ApiPropertyOptional({ description: 'Capacidad de carga en kg (camión)', example: 5000 })
  cargoCapacity?: number;
}
