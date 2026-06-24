import { ValidationPipe } from '@nestjs/common';
import { NestFactory } from '@nestjs/core';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.useGlobalPipes(
    new ValidationPipe({
      transform: true,
      whitelist: true,
      forbidNonWhitelisted: true,
    }),
  );

  const config = new DocumentBuilder()
    .setTitle('Assignments API')
    .setVersion('1.0.0')
    .setDescription(
      'API para asignación de vehículos a propietarios y trazabilidad',
    )
    .addServer('http://localhost:3000', 'Kong Gateway')
    .build();

  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('swagger-ui', app, document, {
    jsonDocumentUrl: '/api-docs',
  });

  await app.listen(process.env.PORT ?? 3000);
}
void bootstrap();
