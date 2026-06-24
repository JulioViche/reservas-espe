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
import { ApiProperty } from '@nestjs/swagger';
import { MotorcycleType } from '../entities/motorcycle.entity';
import { Classification } from '../entities/vehicle.entity';
import { Type } from 'class-transformer';

export class VehicleDto {
  @IsString()
  @IsNotEmpty()
  @Matches(/^[A-Z]{3}-[0-9]{4}$/, {
    message: 'Plate must be in the format ABC-1234',
  })
  @ApiProperty({ description: 'Placa del vehículo', example: 'ABC-1234' })
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
  @ApiProperty({ description: 'Marca del vehículo', example: 'Toyota' })
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
  @ApiProperty({ description: 'Modelo del vehículo', example: 'Corolla' })
  model!: string;

  @IsString()
  @IsNotEmpty()
  @MinLength(4, {
    message: 'Color must be at least 4 characters long',
  })
  @MaxLength(20, {
    message: 'Color must be at most 20 characters long',
  })
  @ApiProperty({ description: 'Color del vehículo', example: 'Rojo' })
  color!: string;

  @IsEnum(Classification)
  @IsNotEmpty()
  @ApiProperty({ description: 'Clasificación del vehículo', enum: Classification, example: Classification.GASOLINE })
  classification!: Classification;

  @IsInt()
  @IsNotEmpty()
  @Min(1885, {
    message: 'Year must be no earlier than 1885',
  })
  @Max(new Date().getFullYear() + 1, {
    message: `Year must be no later than ${new Date().getFullYear() + 1}`,
  })
  @ApiProperty({ description: 'Año del vehículo', example: 2024 })
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
  @ApiProperty({ description: 'Número de puertas', example: 4 })
  doors!: number;

  @IsNumber()
  @IsNotEmpty()
  @Min(0, {
    message: 'Trunk capacity must be a positive number',
  })
  @Max(10000, {
    message: 'Trunk capacity must be less than 10000 liters',
  })
  @ApiProperty({ description: 'Capacidad del maletero en litros', example: 500 })
  trunkCapacity!: number;
}

export class MotorcycleDto extends VehicleDto {
  @IsString()
  @IsNotEmpty()
  @Matches(/^[A-Z]{2}-[0-9]{3}[A-Z]$/, {
    message: 'Plate must be in the format AB-123C',
  })
  @ApiProperty({ description: 'Placa de la motocicleta', example: 'AB-123C' })
  declare plate: string;

  @IsEnum(MotorcycleType)
  @IsNotEmpty()
  @ApiProperty({ description: 'Tipo de motocicleta', enum: MotorcycleType, example: MotorcycleType.SPORT })
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
  @ApiProperty({ description: 'Capacidad de carga en kg', example: 5000 })
  cargoCapacity!: number;
}

export class CreateVehicleDto {
  @IsIn(['car', 'motorcycle', 'truck'])
  @ApiProperty({ description: 'Tipo de vehículo', enum: ['car', 'motorcycle', 'truck'], example: 'car' })
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
  @ApiProperty({
    description: 'Datos específicos del vehículo según el tipo',
    example: {
      plate: 'ABC-1234',
      brand: 'Toyota',
      model: 'Corolla',
      year: 2024,
      color: 'Rojo',
      classification: 'gasoline',
      doors: 4,
      trunkCapacity: 500,
    },
  })
  data!: CarDto | MotorcycleDto | TruckDto;
}
