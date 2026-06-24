import { ApiProperty } from '@nestjs/swagger';

export class VehicleInfoDto {
  @ApiProperty({
    description: 'ID del vehículo',
    example: '123e4567-e89b-12d3-a456-426614174001',
  })
  id: string;

  @ApiProperty({ description: 'Placa del vehículo', example: 'ABC-1234' })
  plate: string;

  @ApiProperty({ description: 'Marca del vehículo', example: 'Toyota' })
  brand: string;

  @ApiProperty({ description: 'Modelo del vehículo', example: 'Corolla' })
  model: string;

  @ApiProperty({ description: 'Tipo de vehículo', example: 'car' })
  type: string;

  @ApiProperty({ description: 'Categoría de energía', example: 'hybrid' })
  category: string;
}

export class FleetResponseDto {
  @ApiProperty({
    description: 'ID del propietario',
    example: '123e4567-e89b-12d3-a456-426614174000',
  })
  userId: string;

  @ApiProperty({ description: 'Vehículos asignados', type: [VehicleInfoDto] })
  vehicles: VehicleInfoDto[];
}
