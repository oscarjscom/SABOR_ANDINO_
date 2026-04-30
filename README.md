## Registro del proceso de desarrollo de nuestra aplicación Sabor Andino.

## Pantalla de Inicio de Sesión — estado inicial
![alt text](docs/login.jpg)
## Pantalla de Inicio de Sesión — estado final
### PROMPT01
Actúa como un Senior UI/UX Engineer especializado en Jetpack Compose. Tu tarea es refactorizar el componente de cabecera en LoginScreen.kt para integrar la identidad visual de 'Sabor Andino'.
Instrucciones técnicas de implementación:
Sustitución de Asset: Reemplaza el Icon genérico (actualmente Icons.Default.Restaurant) ubicado dentro del Surface por un componente Image. Genera una imagen del logo acordé a la idea del aplicativo "Sabor Andino".
Optimización de Escala: Ajusta el tamaño del contenedor Surface de 100.dp a 150.dp para mejorar la legibilidad de los detalles rústicos del logo. Asegúrate de que la imagen use ContentScale.Fit para preservar la relación de aspecto circular del escudo.
Fidelidad de Color: Elimina cualquier propiedad tint o filtro de color aplicado al icono original. El nuevo logotipo debe mostrar su paleta cromática completa (tonos madera, rojizos y amarillos) para mantener la consistencia de marca.
Ajuste de Composición y Jerarquía: Dado que el logotipo ya incluye tipografía integrada ('Sabor Andino' y 'Comida Peruana'), elimina los composables Text redundantes que se encuentran debajo del Spacer. Esto reducirá la carga cognitiva y limpiará la arquitectura de información del header.
Spacing: Ajusta el padding interno del Surface a 8.dp para crear un área de respiro (whitespace) adecuada entre el borde del contenedor translúcido y el activo gráfico.

### PROMPT01 CORRECCIÓN 01:
Para el siguiente cambio, agregar una librería donde pueda agregarse una imagen genérica y diga "Sabor Andino", no modificar UI/UX, la imagen debe mostrarse como un logotipo, generar un logotipo de ser así.

### PROMPT01 CORRECCIÓN 02:
corregir el error de bug, no deja acceder al simulador, al correr los cambios del logotipo.

![alt text](<docs/login  final.jpg>)

## Pantalla Principal — estado inicial
![alt text](docs/home.jpg)
## Pantalla Principal — estado final
### PROMPT02
sugerencias concretas para elevar el diseño y la experiencia de usuario (UX) de Sabor Andino, enfocándome en modernizar el estilo y mejorar la usabilidad:
MenuScreen.kt (Apetito Visual)
   • Imágenes (Crucial): Un menú de comida sin fotos no vende. Implementa AsyncImage (Coil) dentro de las Card.
   ◦ Sugerencia: Usa una estructura de fila dentro de la tarjeta: Imagen a la izquierda (cuadrada con bordes redondeados) y textos a la derecha.
   • Jerarquía de Precios: El precio "S/ 25.0" debe destacar. Usa el colorScheme.primary y un peso Bold para que sea lo primero que el ojo identifique tras el nombre.
   • Filtros (Chips): Los FilterChip son excelentes. Podrías añadir un icono a cada categoría (ej: un icono de bebida para "Bebidas") para una identificación rápida.
   • Empty State: Si la búsqueda o filtro no arroja resultados, muestra una ilustración o un texto amigable, no una pantalla en blanco.

![alt text](docs/home01.png)

## Pantalla de Carta — estado inicial
![alt text](docs/carta.jpg)
## Pantalla de Carta — estado final
### PROMPT03
Al acceder al MenuScreen.kt, añadir imagenes  adaptando una libreria en las cards donde representan los platos de las cartas que estan a la venta, que se adapte a cada cuadro de cada plato.
### CORRECCIÓN DE IMAGENES 
corregir las imagenes a platos de la gastronomia peruana como Ceviche Clásico, Causa Limeña, Lomo Saltado, Ají de gallina, Mazamorra Morada, Chicha morada dentro de las cards mostradas en MenuScreen.kt
#### Observación: 
No hay existencias de platillos gastronómicos peruanos en la libreria donde se extraen las imágenes, generalmente se crea una carpeta img dentro del proyecto y se añaden las fotos o imágenes para dar una mejor presentación del producto a mostrar dentro de la lista de plantillas que ofrece el restaurant,
sin embargo, como ejemplo demostrativo, se añadieron rutas de imagenes extraídos desde el navegador.

![alt text](docs/carta01.png)

## Pantalla detalle de Carta - estado inicial
![alt text](<docs/detalle de orden.jpg>)
## Pantalla detalle de Carta - estado final
### PROMPT04
DetailScreen.kt (Conversión y Claridad)
• Hero Image: El nombre del plato no debería ser lo primero. Lo ideal es una imagen grande en la parte superior (estilo banner) que ocupe el 30% de la pantalla, y que el contenido haga scroll debajo.
• Selector de Cantidad:
◦ Diseño: En lugar de botones de texto + y -, usa FilledIconButton con iconos reales (Icons.Default.Add y Icons.Default.Remove).
◦ Jerarquía: Coloca el selector de cantidad y el precio total en una superficie (Surface) o Card destacada cerca del botón de compra.
• Feedback (UX): Al pulsar "Agregar al pedido", el usuario vuelve atrás sin confirmación. Usa un Snackbar o una pequeña animación para confirmar que el producto se añadió con éxito.
• Precios: Usa un formato consistente. Tienes S/ ${plato.precio} y luego "%.2f".format(...). Crea una función de utilidad para que todos los precios tengan siempre dos decimales y el mismo símbolo.
Sugerencias Generales (app/src/main/java/com/example/sabor_andino/ui/theme/Type.kt)
Para que el diseño se sienta "Andino" y profesional:
- Tipografía Personalizada: En tu archivo Type.kt, define una FontFamily personalizada. Una fuente como Playfair Display para títulos y Inter o Montserrat para cuerpo de texto da un aire de "restaurante gourmet".
- Colores: No te limites a los colores por defecto de Material 3. Define una paleta basada en tonos terracota, ocre o rojos profundos (colores de los Andes) en tu Color.kt.
Ejemplo rápido de mejora para los precios en el menú:
Text(
text = "S/ ${"%.2f".format(plato.precio)}",
style = MaterialTheme.typography.titleLarge.copy(
fontWeight = FontWeight.ExtraBold,
color = MaterialTheme.colorScheme.primary
))

![alt text](<docs/detalle de orden01.png>)

## Pantalla de Orden — estado inicial
![alt text](docs/pedido.jpg)
## Pantalla de Orden — estado final
### PROMPT05
![alt text](<docs/pedido01.png>)

## Pantalla de Usuario — estado inicial
![alt text](docs/perfil.jpg)
## Pantalla de Usuario — estado final
### PROMPT06
![alt text](docs/perfil01.png)