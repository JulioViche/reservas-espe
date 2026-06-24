import {
  EntitySubscriberInterface,
  InsertEvent,
  RemoveEvent,
  EventSubscriber,
} from 'typeorm';
import { Assignment } from '../../assignments/entities/assignment.entity';
import { AuditLog, AuditAction } from '../entities/audit-log.entity';

@EventSubscriber()
export class AssignmentAuditSubscriber implements EntitySubscriberInterface<Assignment> {
  listenTo() {
    return Assignment;
  }

  async afterInsert(event: InsertEvent<Assignment>): Promise<void> {
    const auditLog = event.manager.create(AuditLog, {
      userId: event.entity.userId,
      vehicleId: event.entity.vehicleId,
      action: AuditAction.CREATION,
      timestamp: new Date(),
      newState: event.entity as unknown as Record<string, unknown>,
    });
    await event.manager.save(auditLog);
  }

  async afterRemove(event: RemoveEvent<Assignment>): Promise<void> {
    if (!event.entity) return;

    const auditLog = event.manager.create(AuditLog, {
      userId: event.entity.userId,
      vehicleId: event.entity.vehicleId,
      action: AuditAction.ELIMINATION,
      timestamp: new Date(),
      previousState: event.entity as unknown as Record<string, unknown>,
    });
    await event.manager.save(auditLog);
  }
}
