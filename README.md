# Develop - MercadoLibre
Arquitectura Android que implementa búsqueda de productos y detalle del mismo a través de los API de _MercadoLibre_.

## Dependencias
El proyecto en su mayoría en las librerías de android jetpack (Arquitectura de componentes), estas librerías son proporcionadas directamente por Android. Algunas librerías que se usan son en alpha, o versiones aún no estables para producción, pero qué más da? Cuando sean estables estaremos a la vanguardia y con las mejores prácticas!

##### Core Kotlin | Androidx
Las infaltables!
```
androidx.core:core-ktx
androidx.appcompat:appcompat
```

##### Diseño
Librerias para utilizar los estandares de diseño de material y las funcionalidades de recyclerview!
```
com.google.android.material
androidx.recyclerview:recyclerview
androidx.cardview:cardview
```

##### Activity
Si se requiere, esta dependencia permite registrar un `callback` el `onBackPressed` desde el fragmento y jugar con el `NavController` de navigation :O!
```
androidx.activity:activity-ktx
```

##### Fragment
No mucho que mencionar acá. Para trabajar en conjunto con las dependencias de `lifecycle` y `livedata` esta no puede faltar. Además incluye algunas funcionalidades/extensiones como el `viewLifecycleOwner`, indispensable para `livedata`.
```
androidx.fragment:fragment-ktx
androidx.fragment:fragment-testing
```
    
##### Lifecycle
Nos permite el uso de _livedata_ en conjunto con _viewmodel_ que automáticamente maneja el ciclo de vide de la mano con el _fragmento_. El corazón de la Arquitectura de componentes en Android es esta! Además, aporta extensiones para _coroutines_, _flow_, _livedata_, _room_.
```
androidx.lifecycle:lifecycle-viewmodel-ktx
androidx.lifecycle:lifecycle-livedata-ktx
androidx.lifecycle:lifecycle-viewmodel-savedstate
androidx.lifecycle:lifecycle-common-java8
androidx.lifecycle:lifecycle-service
androidx.lifecycle:lifecycle-process
androidx.arch.core:core-testing
```

##### LiveEvent
Todos sabemos lo tedioso que es que al hacer un giro de pantalla y al recrear la vista se realice una accion no deseada como navegar o mostrar un diálogo de error no deseado. Esta dependencia lo soluciona, lleva el `SingleLiveEvent` a otro nivel permitiendo varios observadores!
```
implementation "com.github.hadilq.liveevent:liveevent:$live_event_version".
```

##### Navigation
No mas `fragmentManager.beginTransaction()`, esta dependencia hace que la navegación entre fragmentos de única actividad sea muy fácil de manipular y permite enfocarse a lo que de verdad importa, las funcionalidades!
```
androidx.navigation:navigation-fragment
androidx.navigation:navigation-ui
androidx.navigation:navigation-fragment-ktx
androidx.navigation:navigation-ui-ktx
androidx.navigation:navigation-dynamic-features-fragment
androidx.navigation:navigation-testing
```

##### Room
Aunque no se implementa aún en este proyecto, por qué no guardar los datos de la ultima consulta de productos/producto y asi, si el usuario vuelve, mostrarle la ultima busqueda que hizo? O guardar las últimas 10 búsquedas que ha hecho y poder sugerir los productos según sus búsquedas?
```
androidx.room:room-runtime
androidx.room:room-ktx
androidx.room:room-compiler
androidx.room:room-testing:$room_version
```

##### Gson
Te imaginas poder logear tus modelos en formato JSON e ir a [JSON Formatter](https://jsonformatter.curiousconcept.com/) y poder analizar mejor cómo se estructuran tus datos? Bueno, Gson es lo que necesitas, no más.
```
com.google.code.gson:gson:$gson_version
```

##### Retrofit
Fácil, seguro, rápido y permite utilizar Coroutines, Executers, RxJava y adicional tus JSON los devuelve en modelos de datos mapeados. Donde encuentras todo eso? Retrofit!

El interceptor es para logear los servicios y poder tener mejor control de qué son las locuras que te envía el backend!
```
com.squareup.retrofit2:converter-gson
com.squareup.retrofit2:retrofit:$retrofit_version
com.squareup.okhttp3:logging-interceptor
```

##### Coroutines
Permite ejecutar fácilmente funciones y cálculos en otros hilos sin bloquear el principal. Revisa el repositorio, podemos consumir uno o varios servicios haciendo un flujo total emitiendo valores ya sea de `loading`, `error` o `success` con `Flow`! Si, está aún en experimental, en alpha y bla bla bla, adiós RxJava :O.
```
org.jetbrains.kotlinx:kotlinx-coroutines-core
org.jetbrains.kotlinx:kotlinx-coroutines-android
org.jetbrains.kotlinx:kotlinx-coroutines-test
```

##### Dagger
Bendito sea el `activityLifecycleCallback` del `application` que permite propagar la inyección de dependencias a través de `activity`, `fragment` y `viewmodel` permitiendo cargar automáticamente las clases que se requieran asi como poderlas reusar (`@Reusable`) o tenerlas globales para cuando se requiera una persistencia en tiempo de ejecución de datos `@Singleton`.
```
implementation "com.google.dagger:dagger:$dagger_version"
implementation "com.google.dagger:dagger-android:$dagger_version"
implementation "com.google.dagger:dagger-android-support:$dagger_version"
kapt "com.google.dagger:dagger-compiler:$dagger_version"
kapt "com.google.dagger:dagger-android-processor:$dagger_version
```

##### Glide
Aburrida librería de imágenes que solamente te permite cargar imágenes desde web, bitmap, drawables u otra?, ademas de manejar cientas de ellas a la vez y mantenerlas en caché. Muy aburrida, no?
```
com.github.bumptech.glide:glide
com.github.bumptech.glide:compiler
```

##### Timber
Logea como los profesionales decían, usa Timber decían. Simple y poderoza para logear errores!
```
com.jakewharton.timber:timber:$timber_version
```

##### JUnit, Espresso y Mockito
Testear repositorios, viewmodel, vistas, click, tipeos, etc.... Estas son las tres librerías más poderosas y usadas para realizar pruebas unitarias al código, algo asi como los tres jinetes del apocalipsis.
```
junit:junit
androidx.arch.core:core-testing
androidx.test:core
androidx.test:runner
droidx.test:rules
est.ext:truth
androidx.test.ext:junit
com.google.truth:truth
androidx.arch.core:core-testing

androidx.test.espresso:espresso-core
androidx.test.espresso:espresso-contrib
androidx.test.espresso:espresso-intents
androidx.test.espresso:espresso-accessibility
androidx.test.espresso:espresso-web
androidx.test.espresso.idling:idling-concurrent
androidx.test.espresso:espresso-idling-resource

org.mockito:mockito-core
org.mockito:mockito-inline
org.mockito:mockito-core
org.mockito:mockito-android
```
## Manejo de errores
Todos los errores o exitosos son manejados a través de un código al cual se asocia un string y este es mostrado a través de un diálogo si se desea, esto es manejado por el `AppCode.kt`, `ExceptionsHandler.kt` y el `BaseFragment` cada uno dedico a una funciona especifica respetando el principio de responsabilidad simple o única de SOLID.

### Developer
Como desarrollador es muy dificil saber qué tipo de error y donde ocurrió solo con un texto, el código de cada error o exitoso sirve para ubicar y orientar donde exactamente está el error y qué lo pudo ocacionar. Inclusive a las exceptions que peudan surguir les asignamos un código y si es necesario creamos las nuestras asignandole un código también.

### Usuario
Los errores en esta implementación son mostrados diálogos, pero es opcional, al utilizar el patron de `Resource` y tener varias emisiones de datos en `Status.ERROR` podemos hacer diferentes funcionalidades, sea ocultar vistas, modificar textos, navegar a otros fragmento o mostrar un diálogo de error.
