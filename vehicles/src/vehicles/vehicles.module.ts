import { Module } from '@nestjs/common';
import { VehiclesService } from './vehicles.service';
import { VehiclesController } from './vehicles.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Vehicle } from './entities/vehicle.entity';
import { Car } from './entities/car.entity';
import { Motorcycle } from './entities/motorcycle.entity';
import { Truck } from './entities/truck.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Vehicle, Car, Motorcycle, Truck])],
  controllers: [VehiclesController],
  providers: [VehiclesService],
  exports: [VehiclesService],
})
export class VehiclesModule {}
