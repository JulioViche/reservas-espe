import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { VehiclesClientService } from './clients/vehicles-client.service';

@Module({
  imports: [ConfigModule],
  providers: [VehiclesClientService],
  exports: [VehiclesClientService],
})
export class CommonModule {}
