package cl.accenture.programatufuturo.proyectofinal.inventario.Conversores;

public class ConversorDataUtilASql {

//    public static void main(String[] args) {
//        java.util.Date uDate = new java.util.Date();
//        System.out.println("Time in java.util.Date is : " + uDate);
//        java.sql.Date sDate = convertUtilToSql(uDate);
//        System.out.println("Time in java.sql.Date is : " + sDate);
//
//    }

    public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
