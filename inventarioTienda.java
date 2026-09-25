import java.util.Scanner;

public class inventarioTienda {

     // Constantes 
    static final int MAX_PRODUCTOS = 20; // cantidad maxima de productos que se pueden guardar
    static final double IVA = 0.19; // porcentaje de IVA
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);   

    // Arreglos para guardar los datos de los productos
    String[] nombre = new String[MAX_PRODUCTOS];
    int[] cantidad = new int[MAX_PRODUCTOS];
    double[] precio = new double[MAX_PRODUCTOS];
    int[] categoria = new int[MAX_PRODUCTOS];

    // Nombres de las categorias
    String[] categorias = {"Granos", "Lacteos", "Snacks", "Bebidas", "Otros" };

    // Suma total por la categoria con una matriz bidimensional
    double[][] matrizInventario = new double[categorias.length][2];

    // contador de los productos ingresados
    int total = 0;

    // Se le pide al usuario los productos 
      System.out.println("Ingrese como minimo 5 productos:");
      for (int i = 0; i < 5; i++) {
        System.out.println("Producto" + " " + (i + 1));
        System.out.println("Nombre:");
        nombre[i] = sc.next();
        System.out.println("cantidad:");
        cantidad[i] = sc.nextInt();
        System.out.println("Precio:");
        precio[i] = sc.nextDouble();
        System.out.println("categorias: 0.Granos 1.Lacteos 2.Snacks 3.Bebidas 4.Otros");
        System.out.println("Escriba el número de la categoria que pertenece:");
        int cat = sc.nextInt();

        //Si el usuario ingresa un numero diferente de los que se les muestra se le manda a la categoria Otros
        if (cat < 0 || cat > 4 ) {
             cat = 4;
}
        categoria[i] = cat;
        total = total + 1;;
      }
      //Si le pregunta al usuario si quiere agregar mas productos
      String seguir = "si";
      while (seguir.equals("si") && total < MAX_PRODUCTOS) { 
        System.out.println("¿Desea agregar otro producto? (si / no):");
        seguir = sc.next();
        if (seguir.equals("si")) {
            System.out.println("Producto:" + " " + (total + 1));
            System.out.println("Nombre:");
            nombre[total] = sc.next();
             System.out.println("cantidad:");
            cantidad[total] = sc.nextInt();
             System.out.println("Precio:");
            precio[total] = sc.nextDouble();
             System.out.println("categorias: 0.Granos 1.Lacteos 2.Snacks 3.Bebidas 4.Otros");
             System.out.println("Escriba el número de la categoria que pertenece:");
             int cat = sc.nextInt();

        if (cat < 0 || cat > 4 ) {
             cat = 4;
}
        //Si el usuario ingresa un numero diferente de los que se les muestra se le manda a la categoria Otros
        categoria[total] = cat;
        total = total + 1;
        }
      }
      // actualizar la cantidad de un producto

      System.out.println("¿Desea actualizar la cantidad de algun producto?");
      String actualizar = sc.next();

      if (actualizar.equals("si")) {
        System.out.println("Ingrese el nombre del producto a actualizar:");
        String prodBuscar = sc.next();
        boolean encontrado = false;

        for(int i = 0;i < total;i++ ) {
          if (nombre[i].equals(prodBuscar)) {
              System.out.println("producto encontrado cantidad actual:"+cantidad[i]);
              System.out.println("ingrese la nueva cantidad");
              cantidad[i] = sc.nextInt();
              System.out.println("cantidad actualizada:");
              encontrado = true;
              break;
          }
        }
        if (!encontrado){
          System.out.println("el producto no existe");
        }
      }

      // calculamos los valores y llenamos la matriz

      double valorTotalInventario = 0;

      for(int i=0; i < total ; i++){
        double valorProducto = cantidad[i] * precio[i];
        valorTotalInventario  =  valorTotalInventario + valorProducto;

        // obtenemos el indice de su categoria

        int cantIndex = categoria[i];

        // llenamos la matriz

        matrizInventario[cantIndex][0] = matrizInventario[cantIndex][0] + cantidad[i];
        matrizInventario[cantIndex][1] = matrizInventario[cantIndex][1] + valorProducto;



      }

      // se imprime el reporte finaL
      System.out.println("============================");
      System.out.println("REPORTE FINAL DE INVENTARIO");
       System.out.println("============================");

      // detalles por producto

      System.out.println("DETALLES DE PRODUCTOS");
      
      for (int i = 0; i < total; i++ ) {
        double valorProducto = cantidad[i] * precio[i];
        String nombreCat = categorias[categoria[i]];

          System.out.println(" producto: " + nombre[i] + 
                           " |   cantidad " + cantidad[i] + 
                           " |   precio " + precio[i] + 
                           " |   total " + valorProducto + 
                           " |   categoria " + nombreCat);
          
      }

      System.out.println("RESUMEN TOTAL POR CATEGORIA");
      
      for (int c = 0; c < categoria.length; c++) {
       
        System.out.println("  * " + categorias[c] + 
                             "   ->   Cantidad Total: " + matrizInventario[c][0] + "unidades" +
                             " |  Valor Acumulado: $" + matrizInventario[c][1]);
    

      }

      double iva = valorTotalInventario * 0.19;
      double totalConIva = valorTotalInventario + iva;

      System.out.println("RESUMEN ECONOMICO");
      System.out.println("subTotal inventario:" + valorTotalInventario);
      System.out.println("iva (19%)" + iva);
      System.out.println("valor total:" + totalConIva);
      



    } 
}