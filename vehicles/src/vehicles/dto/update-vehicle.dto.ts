import {
  IsEnum,
  IsInt,
  IsNotEmpty,
  IsNumber,
  IsOptional,
  IsString,
  Matches,
  Max,
  MaxLength,
  Min,
  MinLength,
} from 'class-validator';
import { MotorcycleType } from '../entities/motorcycle.entity';
import { Classification } from '../entities/vehicle.entity';

export class UpdateVehicleDto {
  @IsOptional()
  @IsString()
  @IsNotEmpty()
  @Matches(/^[A-Z]{3}-[0-9]{4}$/, {
    message: 'Plate must be in the format ABC-1234',
  })
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
  model?: string;

  @IsOptional()
  @IsString()
  @MinLength(4, {
    message: 'Color must be at least 4 characters long',
  })
  @MaxLength(20, {
    message: 'Color must be at most 20 characters long',
  })
  color?: string;

  @IsOptional()
  @IsEnum(Classification)
  classification?: Classification;

  @IsOptional()
  @IsInt()
  @Min(1885, {
    message: 'Year must be no earlier than 1885',
  })
  @Max(new Date().getFullYear() + 1, {
    message: `Year must be no later than ${new Date().getFullYear() + 1}`,
  })
  year?: number;

  @IsOptional()
  @IsInt()
  @Min(2, {
    message: 'Number of doors must be at least 2',
  })
  @Max(5, {
    message: 'Number of doors must be at most 5',
  })
  doors?: number;

  @IsOptional()
  @IsNumber()
  @Min(0, {
    message: 'Trunk capacity must be a positive number',
  })
  @Max(10000, {
    message: 'Trunk capacity must be less than 10000 liters',
  })
  trunkCapacity?: number;

  @IsOptional()
  @IsEnum(MotorcycleType)
  type?: MotorcycleType;

  @IsOptional()
  @IsNumber()
  @Min(0, {
    message: 'Cargo capacity must be a positive number',
  })
  @Max(100000, {
    message: 'Cargo capacity must be less than 100000 kg',
  })
  cargoCapacity?: number;
}
