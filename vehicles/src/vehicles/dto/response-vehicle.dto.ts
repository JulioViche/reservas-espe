export class ResponseVehicleDto {
  id!: string;
  plate!: string;
  brand!: string;
  model!: string;
  color!: string;
  year!: number;
  classification!: string;
  type!: string;
  doors?: number;
  trunkCapacity?: number;
  motorcycleType?: string;
  cargoCapacity?: number;
}
