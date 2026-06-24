import { IsString, Matches, IsNotEmpty } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreateAssignmentDto {
  @IsString()
  @IsNotEmpty()
  @Matches(/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i, {
    message: 'userId must be a valid UUID',
  })
  @ApiProperty({
    description: 'Identificador único del propietario',
    example: '123e4567-e89b-12d3-a456-426614174000',
  })
  userId: string;

  @IsString()
  @IsNotEmpty()
  @Matches(/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/i, {
    message: 'vehicleId must be a valid UUID',
  })
  @ApiProperty({
    description: 'Identificador único del vehículo',
    example: '123e4567-e89b-12d3-a456-426614174001',
  })
  vehicleId: string;
}
