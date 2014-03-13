package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

   Varasto varasto;
   double vertailuTarkkuus = 0.0001;

   @Before
   public void setUp() {
      varasto = new Varasto(10);
   }

   @Test
   public void konstruktoriLuoTyhjanVaraston() {
      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void uudellaVarastollaOikeaTilavuus() {
      assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
   }

   @Test
   public void uudellaVarastollaOikeaMahtuminen() {
      assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void uudellaVarastollaOikeaTilavuusAlle() {
      Varasto pieni = new Varasto(-1);
      assertEquals(0.0, pieni.getTilavuus(), vertailuTarkkuus);
   }
    @Test
   public void uudellaVarastollaOikeaTilavuusAlleOikeaMahtuminen() {
      Varasto pieni = new Varasto(-1);
      assertEquals(0.0, pieni.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void uudellaVarastollaOikeaTilavuusAlleSaldolla() {
      Varasto pieni = new Varasto(-1, 0);
      assertEquals(0.0, pieni.getTilavuus(), vertailuTarkkuus);
   }

   @Test
   public void uudellaVarastollaOikeaTilavuusAlleSaldolla2() {
      Varasto pieni = new Varasto(-1, -3);
      assertEquals(0.0, pieni.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void uudellaVarastollaOikeaTilavuusAlleSaldolla3() {
      Varasto pieni = new Varasto(-1, -1);
      assertEquals(0, pieni.getSaldo(), vertailuTarkkuus);
   }
   @Test
   public void uudellaVarastollaOikeaTilavuusAlleSaldolla4B() {
      Varasto pieni = new Varasto(-1, 3);
      
      //ei jostaan syystä laita tilavuutta oikein???
      assertEquals(0.0, pieni.getTilavuus(), vertailuTarkkuus);
   }
   @Test
   public void uudellaVarastollaOikeaTilavuusYliSaldolla() {
      Varasto suuri = new Varasto(10,-1);
      assertEquals(10, suuri.getTilavuus(), vertailuTarkkuus);
   }
    @Test
   public void uudellaVarastollaOikeaTilavuusYliSaldolla2() {
      Varasto suuri = new Varasto(10,-1);
      assertEquals(0, suuri.getSaldo(), vertailuTarkkuus);
   }
    @Test
   public void uudellaVarastollaOikeaTilavuusYliSaldolla3() {
      Varasto suuri = new Varasto(10,8);
      assertEquals(8, suuri.getSaldo(), vertailuTarkkuus);
   }
    @Test
   public void uudellaVarastollaOikeaTilavuusYliSaldolla4() {
      Varasto suuri = new Varasto(10,12);
      assertEquals(10, suuri.getSaldo(), vertailuTarkkuus);
   }


   @Test
   public void lisaysLisaaSaldoa() {
      varasto.lisaaVarastoon(8);

      // saldon pitäisi olla sama kun lisätty määrä
      assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void lisaysLisaaPienentaaVapaataTilaa() {
      varasto.lisaaVarastoon(8);

      // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
      assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void vaaraLisaysEiMuutaSaldoa() {
      varasto.lisaaVarastoon(-2);

      // saldon pitäisi olla sama kuin alussa
      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void vaaraLisaysEiMuutaMahtumista() {
      varasto.lisaaVarastoon(-2);

      // saldon pitäisi olla sama kuin alussa
      assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void liikaLisaysEiMeneYli() {
      varasto.lisaaVarastoon(12);

      // saldon pitäisi olla sama kuin tilavuus
      assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void liikaLisaysEiMahduEnempää() {
      varasto.lisaaVarastoon(12);

      // ei pitäisi mahtua yhtään enempää
      assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void ottaminenPalauttaaOikeanMaaran() {
      varasto.lisaaVarastoon(8);

      double saatuMaara = varasto.otaVarastosta(2);

      assertEquals(2, saatuMaara, vertailuTarkkuus);
   }

   @Test
   public void ottaminenLisääTilaa() {
      varasto.lisaaVarastoon(8);

      varasto.otaVarastosta(2);

      // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
      assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void ottaminenMuuttaaSaldoa() {
      varasto.lisaaVarastoon(8);

      varasto.otaVarastosta(2);

      // varastossa pitäisi olla saldo 8-2 = 6
      assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void ottaminenVaarallaArvolla() {
      // negatiivisia arvoja ei voi ottaa
      assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
   }

   @Test
   public void ottaminenVaarallaArvollaEiMuutaMahtumista() {
      varasto.lisaaVarastoon(8);

      varasto.otaVarastosta(-1);
      // mahtumisen ei pitäisi muuttua negatiivistesta ottamisesta
      assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

   @Test
   public void ottaminenVaarallaArvollaEiMuutaSaldoa() {
      varasto.lisaaVarastoon(8);

      varasto.otaVarastosta(-1);
      // saldo ei saa muuttua
      assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
   }
 @Test
   public void ottaminenLikaa() {
      varasto.lisaaVarastoon(8);

      // palautusarvon tulisi olla saldo
      assertEquals(8,  varasto.otaVarastosta(10), vertailuTarkkuus);
   } 
 
   @Test
   public void ottaminenLikaaSaldo() {
      varasto.lisaaVarastoon(8);

      varasto.otaVarastosta(10);

      // saldo pitäisi nollautua
      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
   }

   @Test
   public void ottaminenLikaa2() {
      varasto.lisaaVarastoon(8);

      varasto.otaVarastosta(10);

      // varastossa pitäisi olla 10 tilaa
      assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
   }

// @Test
//   public void varastoToString() {
//      assertEquals("saldo = 0, vielä tilaa 10", varasto.toString());
//   }
   @Test
   public void konstr() {
      varasto = new Varasto(-1);
      varasto = new Varasto(0);
      varasto = new Varasto(1, 1);
      varasto = new Varasto(1, 2);
      varasto = new Varasto(-1, 2);
      varasto = new Varasto(-1, -1);
      varasto.toString();
   }
}