package com.company;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VolumeTest {
    @Test
    public void testMToString() {
        Volume volume = new Volume(100, Volume.Measure.m);
        assertEquals("100.0 м^3", volume.toString());
    }

    @Test
    public void testMlToString() {
        Volume volume = new Volume(100, Volume.Measure.ml);
        assertEquals("100.0 мл.", volume.toString());
    }

    @Test
    public void testLToString() {
        Volume volume = new Volume(100, Volume.Measure.l);
        assertEquals("100.0 л.", volume.toString());
    }
    @Test
    public void testBaToString() {
        Volume volume = new Volume(100, Volume.Measure.ba);
        assertEquals("100.0 баррель", volume.toString());
    }
    @Test
    public void testAddNumber() {
        Volume volume = new Volume(100, Volume.Measure.m);
        volume = volume.add(5);
        assertEquals("105.0 м^3", volume.toString());
    }
    @Test
    public void testSubtractNumber() {
        Volume volume = new Volume(100, Volume.Measure.m);
        volume = volume.subtract(5);
        assertEquals("95.0 м^3", volume.toString());
    }

    @Test
    public void testMultiplyByNumber() {
        Volume volume = new Volume(100, Volume.Measure.m);
        volume = volume.multiply(5);
        assertEquals("500.0 м^3", volume.toString());
    }

    @Test
    public void testDivideByNumber() {
        Volume volume = new Volume(100, Volume.Measure.m);
        volume = volume.divide(5);
        assertEquals("20.0 м^3", volume.toString());
    }
    @Test
    public void testConvertMeterToAny() {
        Volume volume;

        volume  = new  Volume (1,  Volume .Measure.m);
        assertEquals("1000000.0 мл.", volume.to( Volume .Measure.ml).toString());

        volume = new  Volume (1000 / 1,  Volume .Measure.m);
        assertEquals("1.0 л.", volume.to( Volume .Measure.l).toString());

        volume = new  Volume (1 * 1,  Volume .Measure.m);
        assertEquals("6.289811 баррель", volume.to( Volume .Measure.ba).toString());
    }
    @Test
    public void testConvertAnyToMeter() {
        Volume volume;

        volume = new Volume(1, Volume.Measure.ml);
        assertEquals("1.0E-6 м^3", volume.to(Volume.Measure.m).toString());

        volume = new Volume(1, Volume.Measure.l);
        assertEquals("1000.0 м^3", volume.to(Volume.Measure.m).toString());

        volume = new Volume(1, Volume.Measure.ba);
        assertEquals("0.15898728912522173 м^3", volume.to(Volume.Measure.m).toString());
    }
    @Test
    public void testAddTwoVolumes() {
        Volume volume1 = new Volume(1000000, Volume.Measure.ml);
        Volume volume2 = new Volume(1, Volume.Measure.m);

        assertEquals(volume1.add(volume2), new Volume(2000000, Volume.Measure.ml));
        assertEquals(volume1.add(volume2), new Volume(2, Volume.Measure.m));
    }

    @Test
    public void testSubtractTwoVolumes() {
        Volume volume1 = new Volume(3500000, Volume.Measure.ml);
        Volume volume2 = new Volume(0.5, Volume.Measure.m);

        assertEquals(volume1.subtract(volume2), new Volume(3000000, Volume.Measure.ml));
        assertEquals(volume1.subtract(volume2), new Volume(3, Volume.Measure.m));
    }


}