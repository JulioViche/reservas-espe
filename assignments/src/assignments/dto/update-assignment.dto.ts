import { ApiPropertyOptional } from '@nestjs/swagger';

export class UpdateAssignmentDto {
  @ApiPropertyOptional({
    description: 'Estado activo de la asignación',
    example: false,
  })
  isActive?: boolean;
}
