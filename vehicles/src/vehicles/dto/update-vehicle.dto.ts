import {
  IsEnum,
  IsInt,
  IsNotEmpty,
  IsOptional,
  IsString,
  Matches,
  Max,
  MaxLength,
  Min,
  MinLength,
} from 'class-validator';
import { ApiPropertyOptional } from '@nestjs/swagger';
import { Classification } from '../entities/vehicle.entity';

export class UpdateVehicleDto {
  @IsOptional()
  @IsString()
  @IsNotEmpty()
  @Matches(/^[A-Z]{3}-[0-9]{4}$/, {
    message: 'Plate must be in the format ABC-1234',
  })
  @ApiPropertyOptional({ description: 'Placa del vehículo', example: 'ABC-1234' })
  plate?: string;

  @IsOptional()
  @IsString()
  @MinLength(2, {
    message: 'Brand must be at least 2 characters long',
  })
  @MaxLength(50, {
    message: 'Brand must be at most 50 characters long',
  })
  @Matches(/^[A-Za-zÀ-ÿ]+(?:[ -][A-Za-zÀ-ÿ]+)*$/, {
    message: 'Brand must contain only letters and spaces',
  })
  @ApiPropertyOptional({ description: 'Marca del vehículo', example: 'Toyota' })
  brand?: string;

  @IsOptional()
  @IsString()
  @MinLength(1, {
    message: 'Model must be at least 1 character long',
  })
  @MaxLength(50, {
    message: 'Model must be at most 50 characters long',
  })
  @Matches(/^[A-Za-z0-9À-ÿ]+(?:[ -][A-Za-z0-9À-ÿ]+)*$/, {
    message: 'Model must contain only letters, numbers and spaces',
  })
  @ApiPropertyOptional({ description: 'Modelo del vehículo', example: 'Corolla' })
  model?: string;

  @IsOptional()
  @IsString()
  @MinLength(4, {
    message: 'Color must be at least 4 characters long',
  })
  @MaxLength(20, {
    message: 'Color must be at most 20 characters long',
  })
  @ApiPropertyOptional({ description: 'Color del vehículo', example: 'Rojo' })
  color?: string;

  @IsOptional()
  @IsEnum(Classification)
  @ApiPropertyOptional({ description: 'Clasificación del vehículo', enum: Classification, example: Classification.GASOLINE })
  classification?: Classification;

  @IsOptional()
  @IsInt()
  @Min(1885, {
    message: 'Year must be no earlier than 1885',
  })
  @Max(new Date().getFullYear() + 1, {
    message: `Year must be no later than ${new Date().getFullYear() + 1}`,
  })
  @ApiPropertyOptional({ description: 'Año del vehículo', example: 2024 })
  year?: number;
}
