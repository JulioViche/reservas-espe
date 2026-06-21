import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { VehiclesService } from './vehicles.service';
import { CreateVehicleDto } from './dto/create-vehicle.dto';
import { UpdateVehicleDto } from './dto/update-vehicle.dto';
import { ResponseVehicleDto } from './dto/response-vehicle.dto';

@Controller('vehicles')
export class VehiclesController {
  constructor(private readonly vehiclesService: VehiclesService) {}

  @Post()
  create(
    @Body() createVehicleDto: CreateVehicleDto,
  ): Promise<ResponseVehicleDto> {
    return this.vehiclesService.create(createVehicleDto);
  }

  @Get()
  findAll(): Promise<ResponseVehicleDto[]> {
    return this.vehiclesService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string): Promise<ResponseVehicleDto> {
    return this.vehiclesService.findOne(id);
  }

  @Patch(':id')
  update(
    @Param('id') id: string,
    @Body() updateVehicleDto: UpdateVehicleDto,
  ): Promise<ResponseVehicleDto> {
    return this.vehiclesService.update(id, updateVehicleDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string): Promise<void> {
    return this.vehiclesService.remove(id);
  }
}
