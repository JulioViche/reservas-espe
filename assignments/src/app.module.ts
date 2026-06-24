import { Module } from '@nestjs/common';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AssignmentsModule } from './assignments/assignments.module';
import { AuditModule } from './audit/audit.module';
import { Assignment } from './assignments/entities/assignment.entity';
import { AuditLog } from './audit/entities/audit-log.entity';
import { AssignmentAuditSubscriber } from './audit/subscribers/assignment-audit.subscriber';
import { CommonModule } from './common/common.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
      envFilePath: '.env',
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      useFactory: (configService: ConfigService) => ({
        type: 'postgres',
        host: configService.get('DB_HOST'),
        port: +configService.get('DB_PORT'),
        username: configService.get('DB_USERNAME'),
        password: configService.get('DB_PASSWORD'),
        database: configService.get('DB_NAME'),
        entities: [Assignment, AuditLog],
        subscribers: [AssignmentAuditSubscriber],
        synchronize: true,
        logging: true,
      }),
      inject: [ConfigService],
    }),
    AssignmentsModule,
    AuditModule,
    CommonModule,
  ],
})
export class AppModule {}
