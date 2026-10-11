package com.luciano.drawar

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView
    private val CAMERA_PERMISSION_CODE = 100
    private val STORAGE_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vista de dibujo
        drawingView = findViewById(R.id.drawingView)

        // Botones de colores
        findViewById<Button>(R.id.btnColorBlack).setOnClickListener {
            drawingView.setColor(Color.BLACK)
            Toast.makeText(this, "Color: Negro", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnColorRed).setOnClickListener {
            drawingView.setColor(Color.RED)
            Toast.makeText(this, "Color: Rojo", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnColorBlue).setOnClickListener {
            drawingView.setColor(Color.BLUE)
            Toast.makeText(this, "Color: Azul", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnColorGreen).setOnClickListener {
            drawingView.setColor(Color.GREEN)
            Toast.makeText(this, "Color: Verde", Toast.LENGTH_SHORT).show()
        }

        // Botón borrador
        findViewById<Button>(R.id.btnEraser).setOnClickListener {
            drawingView.enableEraser()
            Toast.makeText(this, "Modo: Borrador", Toast.LENGTH_SHORT).show()
        }

        // Botón limpiar
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            drawingView.clear()
            Toast.makeText(this, "Lienzo limpiado", Toast.LENGTH_SHORT).show()
        }

        // Botón guardar
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            checkStoragePermission()
        }

        // Verificar permisos de cámara
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }
    }

    private fun checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_CODE
                )
            } else {
                saveDrawing()
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_CODE
                )
            } else {
                saveDrawing()
            }
        }
    }

    private fun saveDrawing() {
        try {
            val bitmap = drawingView.getBitmap()
            if (bitmap != null) {
                val fileName = "DrawAR_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())}.png"

                val picturesDir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "DrawAR")
                } else {
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DrawAR")
                }

                if (!picturesDir.exists()) {
                    picturesDir.mkdirs()
                }

                val file = File(picturesDir, fileName)
                val fos = FileOutputStream(file)
                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, fos)
                fos.close()

                Toast.makeText(this, "Imagen guardada: $fileName", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveDrawing()
                } else {
                    Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
