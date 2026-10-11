# DrawAR - App de Dibujo con AR

## ⚡ Instrucciones para compilar con GitHub Actions

### Paso 1: Clonar el repo

```bash
cd C:\Users\TuUsuario\Desktop
git clone https://github.com/TU_USUARIO/DrawAR.git
cd DrawAR
```

Reemplaza `TU_USUARIO` con tu usuario de GitHub.

### Paso 2: Copiar archivos del proyecto

1. Abre el archivo `.gitignore` que está en la carpeta (ya está creado)
2. Copia TODOS los archivos que ves en la carpeta `drawar-github` al repo local
3. Incluyendo:
   - `app/` (carpeta)
   - `.github/` (carpeta)
   - `build.gradle.kts`
   - `settings.gradle.kts`
   - `gradle.properties`
   - `.gitignore`

### Paso 3: Push a GitHub

```bash
git add .
git commit -m "Initial commit: DrawAR app"
git push origin main
```

### Paso 4: Espera a que se compile

1. Ve a: https://github.com/TU_USUARIO/DrawAR
2. Click en la pestaña **"Actions"**
3. Espera a que el workflow "Build APK" termine (verde = éxito)
4. Toma ~2-3 minutos

### Paso 5: Descarga el APK

1. En la página de Actions, haz click en el último build
2. Baja hasta "Artifacts"
3. Descarga **app-debug.apk**
4. Pasa al teléfono
5. Instala y abre

¡Listo! La app debería funcionar.

---

## 📱 Estructura del Proyecto

```
DrawAR/
├── .github/workflows/build.yml    (GitHub Actions)
├── app/
│   ├── src/main/
│   │   ├── java/com/luciano/drawar/
│   │   │   ├── MainActivity.kt
│   │   │   └── DrawingView.kt
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml
│   │   │   └── values/strings.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── .gitignore
```

---

¡Éxito! 🚀
