package org.example.eksamenbackend.Config;

import org.example.eksamenbackend.Model.*;
import org.example.eksamenbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

@org.springframework.stereotype.Component
public class InitData implements CommandLineRunner {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    BillOfMaterialsRepository billOfMaterialsRepository;

    @Autowired
    BomLineRepository bomLineRepository;



    @Override
    public void run(String... args) throws Exception{

        //Vores Suppliers navn og adresse
        Supplier sup1 = new Supplier();
        sup1.setName("Nordic Electro Parts");
        sup1.setAddress("Århusgade 1, København 2100");
        supplierRepository.save(sup1);

        Supplier sup2 = new Supplier();
        sup2.setName("ScanTech Components");
        sup2.setAddress("Campusvej 55, 5230 Odense M");
        supplierRepository.save(sup2);

        Supplier sup3 = new Supplier();
        sup3.setName("Lyngby Circuit Supply");
        sup3.setAddress("Nørgaardsvej 4, 2800 Kongens Lyngby");
        supplierRepository.save(sup3);

        //Vores 10 Komponenter
        //1 LED (BOM)
        Component comp1 = new Component();
        comp1.setInternalNumber(1001);
        comp1.setExternalNumber("LED-5MM-RED");
        comp1.setDiscontinued(false);
        comp1.setSupplier(sup1);
        componentRepository.save(comp1);

        Component comp2 = new Component();
        comp2.setInternalNumber(1002);
        comp2.setExternalNumber("RES-1K-1/4W");
        comp2.setDiscontinued(false);
        comp2.setSupplier(sup1);
        componentRepository.save(comp2);

        Component comp3 = new Component();
        comp3.setInternalNumber(1003);
        comp3.setExternalNumber("BAT-HOLDER-9V-SNAP");
        comp3.setDiscontinued(false);
        comp3.setSupplier(sup2);
        componentRepository.save(comp3);

        Component comp4 = new Component();
        comp4.setInternalNumber(1004);
        comp4.setExternalNumber("BAT-9V-ALKALINE");
        comp4.setDiscontinued(false);
        comp4.setSupplier(sup3);
        componentRepository.save(comp4);

        Component comp5 = new Component();
        comp5.setInternalNumber(1005);
        comp5.setExternalNumber("SWITCH-SPST-MINI");
        comp5.setDiscontinued(false);
        comp5.setSupplier(sup1);
        componentRepository.save(comp5);

        Component comp6 = new Component();
        comp6.setInternalNumber(1006);
        comp6.setExternalNumber("CAP-100UF-16V");
        comp6.setDiscontinued(false);
        comp6.setSupplier(sup1);
        componentRepository.save(comp6);

        Component comp7 = new Component();
        comp7.setInternalNumber(1007);
        comp7.setExternalNumber("TRANS-NPN-2N2222");
        comp7.setDiscontinued(false);
        comp7.setSupplier(sup3);
        componentRepository.save(comp7);

        Component comp8 = new Component();
        comp8.setInternalNumber(1008);
        comp8.setExternalNumber("BUZZER-5V-ACTIVE");
        comp8.setDiscontinued(false);
        comp8.setSupplier(sup3);
        componentRepository.save(comp8);

        Component comp9 = new Component();
        comp9.setInternalNumber(1009);
        comp9.setExternalNumber("UMP-WIRE-MALE-MALE");
        comp9.setDiscontinued(false);
        comp9.setSupplier(sup2);
        componentRepository.save(comp9);

        Component comp10 = new Component();
        comp10.setInternalNumber(1010);
        comp10.setExternalNumber("FUSE-1A-FAST");
        comp10.setDiscontinued(false);
        comp10.setSupplier(sup3);
        componentRepository.save(comp10);

        //Vores almindelige bestilling
        Order order1 = new Order();
        order1.setTrackingCode("ORD-100");
        order1.setSupplier(sup2);
        order1.setOrderedDate(LocalDate.now().minusDays(1));
        order1.setExpectedDelivery(LocalDate.now().plusDays(3));
        orderRepository.save(order1);

        //Aktiv bestilling (Ikke leveret endnu)
        Order activeOrder = new Order();
        activeOrder.setTrackingCode("ACT-200");
        activeOrder.setSupplier(sup3);
        activeOrder.setOrderedDate(LocalDate.now().minusDays(1));
        activeOrder.setExpectedDelivery(LocalDate.now().plusDays(5));
        orderRepository.save(activeOrder);


        OrderLine ol1 = new OrderLine();
        ol1.setOrder(activeOrder);
        ol1.setComponent(comp1);
        ol1.setQuantity(10);
        orderLineRepository.save(ol1);

        OrderLine ol2 = new OrderLine();
        ol2.setOrder(activeOrder);
        ol2.setComponent(comp2);
        ol2.setQuantity(10);
        orderLineRepository.save(ol2);

        OrderLine ol3 = new OrderLine();
        ol3.setOrder(activeOrder);
        ol3.setComponent(comp3);
        ol3.setQuantity(10);
        orderLineRepository.save(ol3);

        //Vores færdig bestilling
        Order orderDone = new Order();
        orderDone.setTrackingCode("FIN-300");
        orderDone.setSupplier(sup1);
        orderDone.setOrderedDate(LocalDate.now().minusDays(2));
        orderDone.setExpectedDelivery(LocalDate.now().plusDays(5));
        orderDone.setReceivedDate(LocalDate.now());
        orderRepository.save(orderDone);

        OrderLine ol4 = new OrderLine();
        ol4.setOrder(orderDone);
        ol4.setComponent(comp4);
        ol4.setQuantity(100);
        orderLineRepository.save(ol4);

        //Vores BOM
        BillOfMaterials bom = new BillOfMaterials();
        bom.setName("Lysende LED");
        billOfMaterialsRepository.save(bom);

        //Vores BOM Lines
        BomLine b1 = new BomLine();
        b1.setBom(bom);
        b1.setComponent(comp1); // LED
        b1.setQuantity(1);
        bomLineRepository.save(b1);

        BomLine b2 = new BomLine();
        b2.setBom(bom);
        b2.setComponent(comp2); // resistor
        b2.setQuantity(1);
        bomLineRepository.save(b2);

        BomLine b3 = new BomLine();
        b3.setBom(bom);
        b3.setComponent(comp3); // batteriholder
        b3.setQuantity(1);
        bomLineRepository.save(b3);

        BomLine b4 = new BomLine();
        b4.setBom(bom);
        b4.setComponent(comp4); // 9V batteri
        b4.setQuantity(1);
        bomLineRepository.save(b4);

        //Ordre kan bestilles
        comp1.setOrdereable(true);
        comp2.setOrdereable(true);
        comp3.setOrdereable(true);

        //Ordre kan ikke bestilles
        comp4.setOrdereable(false);
        comp5.setOrdereable(false);

    }
}
