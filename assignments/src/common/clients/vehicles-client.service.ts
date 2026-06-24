import { Injectable, NotFoundException } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import axios, { AxiosResponse } from 'axios';

export interface VehicleResponse {
  id: string;
  plate: string;
  brand: string;
  model: string;
  color: string;
  year: number;
  classification: string;
  type: string;
}

@Injectable()
export class VehiclesClientService {
  private readonly baseUrl: string;

  constructor(private readonly configService: ConfigService) {
    this.baseUrl = this.configService.get<string>(
      'VEHICLES_API_URL',
      'http://vehicles-api:3000',
    );
  }

  async getVehicle(id: string): Promise<VehicleResponse> {
    try {
      const response: AxiosResponse<VehicleResponse> = await axios.get(
        `${this.baseUrl}/vehicles/${id}`,
      );
      return response.data;
    } catch {
      throw new NotFoundException(`Vehicle ${id} not found`);
    }
  }
}
