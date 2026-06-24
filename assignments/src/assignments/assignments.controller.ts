import {
  Controller,
  Get,
  Post,
  Patch,
  Delete,
  Body,
  Param,
  HttpCode,
  HttpStatus,
} from '@nestjs/common';
import { ApiTags, ApiOperation, ApiResponse, ApiParam } from '@nestjs/swagger';
import { AssignmentsService } from './assignments.service';
import { CreateAssignmentDto } from './dto/create-assignment.dto';
import { UpdateAssignmentDto } from './dto/update-assignment.dto';
import { Assignment } from './entities/assignment.entity';
import { FleetResponseDto } from './dto/fleet-response.dto';
import { AuditService } from '../audit/audit.service';
import { AuditLog } from '../audit/entities/audit-log.entity';

@ApiTags('Assignments')
@Controller('assignments')
export class AssignmentsController {
  constructor(
    private readonly assignmentsService: AssignmentsService,
    private readonly auditService: AuditService,
  ) {}

  @Post()
  @ApiOperation({ summary: 'Asignar vehículo a propietario' })
  @ApiResponse({ status: 201, description: 'Asignación creada exitosamente' })
  @ApiResponse({ status: 400, description: 'Datos de entrada inválidos' })
  @ApiResponse({ status: 409, description: 'El vehículo ya está asignado' })
  create(@Body() dto: CreateAssignmentDto): Promise<Assignment> {
    return this.assignmentsService.create(dto);
  }

  @Patch(':userId/:vehicleId')
  @ApiOperation({ summary: 'Modificar asignación de vehículo' })
  @ApiParam({ name: 'userId', description: 'Identificador del propietario', example: '123e4567-e89b-12d3-a456-426614174000' })
  @ApiParam({ name: 'vehicleId', description: 'Identificador del vehículo', example: '123e4567-e89b-12d3-a456-426614174001' })
  @ApiResponse({ status: 200, description: 'Asignación modificada' })
  @ApiResponse({ status: 400, description: 'Datos de entrada inválidos' })
  @ApiResponse({ status: 404, description: 'Asignación no encontrada' })
  update(
    @Param('userId') userId: string,
    @Param('vehicleId') vehicleId: string,
    @Body() updates: UpdateAssignmentDto,
  ): Promise<Assignment> {
    return this.assignmentsService.update(userId, vehicleId, updates);
  }

  @Delete(':userId/:vehicleId')
  @HttpCode(HttpStatus.NO_CONTENT)
  @ApiOperation({ summary: 'Eliminar asignación de vehículo' })
  @ApiParam({ name: 'userId', description: 'Identificador del propietario', example: '123e4567-e89b-12d3-a456-426614174000' })
  @ApiParam({ name: 'vehicleId', description: 'Identificador del vehículo', example: '123e4567-e89b-12d3-a456-426614174001' })
  @ApiResponse({ status: 204, description: 'Asignación eliminada' })
  @ApiResponse({ status: 404, description: 'Asignación no encontrada' })
  async remove(
    @Param('userId') userId: string,
    @Param('vehicleId') vehicleId: string,
  ): Promise<void> {
    await this.assignmentsService.remove(userId, vehicleId);
  }

  @Get('owner/:userId')
  @ApiOperation({ summary: 'Consultar flota por propietario' })
  @ApiParam({ name: 'userId', description: 'Identificador del propietario', example: '123e4567-e89b-12d3-a456-426614174000' })
  @ApiResponse({
    status: 200,
    description: 'Flota del propietario con detalles de vehículos',
    type: FleetResponseDto,
  })
  getFleet(@Param('userId') userId: string): Promise<FleetResponseDto> {
    return this.assignmentsService.getFleetByOwner(userId);
  }

  @Get('audit/:userId/:vehicleId')
  @ApiOperation({ summary: 'Obtener trazabilidad de una asignación' })
  @ApiParam({ name: 'userId', description: 'Identificador del propietario', example: '123e4567-e89b-12d3-a456-426614174000' })
  @ApiParam({ name: 'vehicleId', description: 'Identificador del vehículo', example: '123e4567-e89b-12d3-a456-426614174001' })
  @ApiResponse({ status: 200, description: 'Historial de auditoría' })
  getAudit(
    @Param('userId') userId: string,
    @Param('vehicleId') vehicleId: string,
  ): Promise<AuditLog[]> {
    return this.auditService.findByAssignment(userId, vehicleId);
  }
}
