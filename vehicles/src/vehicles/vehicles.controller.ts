import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { ApiTags, ApiOperation, ApiResponse } from '@nestjs/swagger';
import { VehiclesService } from './vehicles.service';
import { CreateVehicleDto } from './dto/create-vehicle.dto';
import { UpdateVehicleDto } from './dto/update-vehicle.dto';
import { ResponseVehicleDto } from './dto/response-vehicle.dto';

@ApiTags('Vehicles')
@Controller('vehicles')
export class VehiclesController {
  constructor(private readonly vehiclesService: VehiclesService) {}

  @Post()
  @ApiOperation({ summary: 'Crear un vehículo', description: 'Registra un nuevo vehículo (automóvil, motocicleta o camión)' })
  @ApiResponse({ status: 201, description: 'Vehículo creado exitosamente', type: ResponseVehicleDto })
  @ApiResponse({ status: 400, description: 'Datos de entrada inválidos' })
  create(
    @Body() createVehicleDto: CreateVehicleDto,
  ): Promise<ResponseVehicleDto> {
    return this.vehiclesService.create(createVehicleDto);
  }

  @Get()
  @ApiOperation({ summary: 'Listar todos los vehículos', description: 'Obtiene todos los vehículos registrados' })
  @ApiResponse({ status: 200, description: 'Lista de vehículos obtenida exitosamente', type: [ResponseVehicleDto] })
  findAll(): Promise<ResponseVehicleDto[]> {
    return this.vehiclesService.findAll();
  }

  @Get(':id')
  @ApiOperation({ summary: 'Obtener vehículo por ID', description: 'Busca un vehículo por su identificador único' })
  @ApiResponse({ status: 200, description: 'Vehículo encontrado', type: ResponseVehicleDto })
  @ApiResponse({ status: 404, description: 'Vehículo no encontrado' })
  findOne(@Param('id') id: string): Promise<ResponseVehicleDto> {
    return this.vehiclesService.findOne(id);
  }

  @Patch(':id')
  @ApiOperation({ summary: 'Actualizar un vehículo', description: 'Actualiza parcialmente los datos de un vehículo existente' })
  @ApiResponse({ status: 200, description: 'Vehículo actualizado exitosamente', type: ResponseVehicleDto })
  @ApiResponse({ status: 404, description: 'Vehículo no encontrado' })
  @ApiResponse({ status: 400, description: 'Datos de entrada inválidos' })
  update(
    @Param('id') id: string,
    @Body() updateVehicleDto: UpdateVehicleDto,
  ): Promise<ResponseVehicleDto> {
    return this.vehiclesService.update(id, updateVehicleDto);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Eliminar un vehículo', description: 'Elimina (soft delete) un vehículo del sistema' })
  @ApiResponse({ status: 204, description: 'Vehículo eliminado exitosamente' })
  @ApiResponse({ status: 404, description: 'Vehículo no encontrado' })
  remove(@Param('id') id: string): Promise<void> {
    return this.vehiclesService.remove(id);
  }
}
