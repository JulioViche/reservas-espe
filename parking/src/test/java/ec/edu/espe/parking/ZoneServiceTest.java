package ec.edu.espe.parking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.parking.dtos.ZoneRequestDto;
import ec.edu.espe.parking.dtos.ZoneResponseDto;
import ec.edu.espe.parking.entities.ZoneType;
import ec.edu.espe.parking.services.ZoneService;

@SpringBootTest
@Transactional
class ZoneServiceTest {

    @Autowired
    private ZoneService zoneService;

    @Test
    void testCreateZone() {
        ZoneRequestDto request = ZoneRequestDto.builder()
                .name("VIP Norte")
                .description("Zona VIP del norte")
                .type(ZoneType.VIP)
                .build();

        ZoneResponseDto response = zoneService.createZone(request);

        assertNotNull(response.getId());
        assertEquals("VIP Norte", response.getName());
        assertTrue(response.isActive());
        assertTrue(response.getCode().startsWith("ZONE-VIP-"));
    }

    @Test
    void testGetAllZones() {
        ZoneRequestDto request = ZoneRequestDto.builder()
                .name("VIP Norte")
                .type(ZoneType.VIP)
                .build();
        zoneService.createZone(request);

        List<ZoneResponseDto> zones = zoneService.getAllZones();
        assertFalse(zones.isEmpty());
    }

    @Test
    void testUpdateZone() {
        ZoneRequestDto request = ZoneRequestDto.builder()
                .name("VIP Norte")
                .type(ZoneType.VIP)
                .build();
        ZoneResponseDto created = zoneService.createZone(request);

        ZoneRequestDto updateRequest = ZoneRequestDto.builder()
                .name("VIP Sur")
                .type(ZoneType.VIP)
                .build();
        ZoneResponseDto updated = zoneService.updateZone(created.getId(), updateRequest);

        assertEquals("VIP Sur", updated.getName());
    }

    @Test
    void testSwitchZoneStatus() {
        ZoneRequestDto request = ZoneRequestDto.builder()
                .name("VIP Norte")
                .type(ZoneType.VIP)
                .build();
        ZoneResponseDto created = zoneService.createZone(request);
        assertTrue(created.isActive());

        zoneService.switchZoneStatus(created.getId());

        ZoneResponseDto afterSwitch = zoneService.getAllZones().stream()
                .filter(z -> z.getId().equals(created.getId()))
                .findFirst().orElseThrow();
        assertFalse(afterSwitch.isActive());
    }
}
