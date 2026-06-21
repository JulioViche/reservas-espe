import {
  IsEnum,
  IsIn,
  IsInt,
  IsNotEmpty,
  IsNumber,
  IsString,
  Matches,
  Max,
  MaxLength,
  Min,
  MinLength,
  ValidateNested,
} from 'class-validator';
import { MotorcycleType } from '../entities/motorcycle.entity';
import { Classification } from '../entities/vehicle.entity';
import { Type } from 'class-transformer';

export class VehicleDto {
  @IsString()
  @IsNotEmpty()
  @Matches(/^[A-Z]{3}-[0-9]{4}$/, {
    message: 'Plate must be in the format ABC-1234',
  })
  plate!: string;

  @IsString()
  @IsNotEmpty()
  @MinLength(2, {
    message: 'Brand must be at least 2 characters long',
  })
  @MaxLength(50, {
    message: 'Brand must be at most 50 characters long',
  })
  @Matches(/^[A-Za-zÀ-ÿ]+(?:[ -][A-Za-zÀ-ÿ]+)*$/, {
    message: 'Brand must contain only letters and spaces',
  })
  brand!: string;

  @IsString()
  @IsNotEmpty()
  @MinLength(1, {
    message: 'Model must be at least 1 character long',
  })
  @MaxLength(50, {
    message: 'Model must be at most 50 characters long',
  })
  @Matches(/^[A-Za-z0-9À-ÿ]+(?:[ -][A-Za-z0-9À-ÿ]+)*$/, {
    message: 'Model must contain only letters, numbers and spaces',
  })
  model!: string;

  @IsString()
  @IsNotEmpty()
  @MinLength(4, {
    message: 'Color must be at least 4 characters long',
  })
  @MaxLength(20, {
    message: 'Color must be at most 20 characters long',
  })
  color!: string;

  @IsEnum(Classification)
  @IsNotEmpty()
  classification!: Classification;

  @IsInt()
  @IsNotEmpty()
  @Min(1885, {
    message: 'Year must be no earlier than 1885',
  })
  @Max(new Date().getFullYear() + 1, {
    message: `Year must be no later than ${new Date().getFullYear() + 1}`,
  })
  year!: number;
}

export class CarDto extends VehicleDto {
  @IsInt()
  @IsNotEmpty()
  @Min(2, {
    message: 'Number of doors must be at least 2',
  })
  @Max(5, {
    message: 'Number of doors must be at most 5',
  })
  doors!: number;

  @IsNumber()
  @IsNotEmpty()
  @Min(0, {
    message: 'Trunk capacity must be a positive number',
  })
  @Max(10000, {
    message: 'Trunk capacity must be less than 10000 liters',
  })
  trunkCapacity!: number;
}

export class MotorcycleDto extends VehicleDto {
  @IsString()
  @IsNotEmpty()
  @Matches(/^[A-Z]{2}-[0-9]{3}[A-Z]$/, {
    message: 'Plate must be in the format AB-123C',
  })
  declare plate: string;

  @IsEnum(MotorcycleType)
  @IsNotEmpty()
  type!: MotorcycleType;
}

export class TruckDto extends VehicleDto {
  @IsNumber()
  @IsNotEmpty()
  @Min(0, {
    message: 'Cargo capacity must be a positive number',
  })
  @Max(100000, {
    message: 'Cargo capacity must be less than 100000 kg',
  })
  cargoCapacity!: number;
}

export class CreateVehicleDto {
  @IsIn(['car', 'motorcycle', 'truck'])
  type!: string;

  @ValidateNested()
  @Type((opts) => {
    const object = opts?.object as CreateVehicleDto;
    if (!object) return VehicleDto;

    switch (object.type) {
      case 'car':
        return CarDto;
      case 'motorcycle':
        return MotorcycleDto;
      case 'truck':
        return TruckDto;
      default:
        return VehicleDto;
    }
  })
  data!: CarDto | MotorcycleDto | TruckDto;
}
