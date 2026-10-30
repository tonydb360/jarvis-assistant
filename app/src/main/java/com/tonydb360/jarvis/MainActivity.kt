package com.tonydb360.jarvis

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val Background = Color(0xFF050B12)
private val Panel = Color(0xFF0B1722)
private val Cyan = Color(0xFF49DDF5)
private val Muted = Color(0xFF7895A5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                JarvisDashboard(isOnline = isNetworkAvailable(this))
            }
        }
    }
}

private fun isNetworkAvailable(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = manager.activeNetwork ?: return false
    val capabilities = manager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

@Composable
private fun JarvisDashboard(isOnline: Boolean) {
    var currentTime by remember { mutableStateOf("--:--") }
    var currentDate by remember { mutableStateOf("") }
    var initialised by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            val now = Date()
            currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(now)
            currentDate = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(now)
            delay(1000)
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Background) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("STARK INDUSTRIES", color = Muted, fontSize = 11.sp, letterSpacing = 4.sp)
                Spacer(Modifier.height(8.dp))
                Text("J.A.R.V.I.S.", color = Cyan, fontSize = 29.sp, fontWeight = FontWeight.Light, letterSpacing = 5.sp)
                Spacer(Modifier.height(5.dp))
                Text("PERSONAL ASSISTANT SYSTEM", color = Muted, fontSize = 10.sp, letterSpacing = 2.sp)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(238.dp)) {
                    Canvas(Modifier.fillMaxSize()) {
                        val center = Offset(size.width / 2, size.height / 2)
                        drawCircle(Cyan.copy(alpha = 0.10f), radius = size.minDimension * 0.46f, center = center)
                        drawCircle(Cyan.copy(alpha = 0.75f), radius = size.minDimension * 0.43f, center = center, style = Stroke(width = 2.dp.toPx()))
                        drawCircle(Cyan.copy(alpha = 0.28f), radius = size.minDimension * 0.34f, center = center, style = Stroke(width = 1.dp.toPx()))
                        drawArc(Cyan, -90f, 255f, false, style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
                            topLeft = Offset(size.width * 0.12f, size.height * 0.12f),
                            size = androidx.compose.ui.geometry.Size(size.width * 0.76f, size.height * 0.76f))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("SYSTEM", color = Muted, fontSize = 10.sp, letterSpacing = 3.sp)
                        Text(if (initialised) "READY" else "STANDBY", color = Cyan, fontSize = 25.sp, letterSpacing = 3.sp)
                        Text("CORE 01", color = Muted, fontSize = 10.sp, letterSpacing = 2.sp)
                    }
                }
                Spacer(Modifier.height(26.dp))
                Text(currentTime, color = Color.White, fontSize = 38.sp, fontWeight = FontWeight.Light, letterSpacing = 3.sp)
                Spacer(Modifier.height(5.dp))
                Text(currentDate.uppercase(Locale.getDefault()), color = Muted, fontSize = 10.sp, letterSpacing = 1.5.sp)
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                StatusPanel("NETWORK", if (isOnline) "CONNECTED" else "OFFLINE", isOnline)
                StatusPanel("VOICE INTERFACE", "NOT CONFIGURED", false)
                StatusPanel("AI CONNECTION", "NOT CONFIGURED", false)
                Spacer(Modifier.height(4.dp))
                Surface(
                    onClick = { initialised = !initialised },
                    color = Cyan.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(Modifier.padding(17.dp), contentAlignment = Alignment.Center) {
                        Text(if (initialised) "SYSTEM INITIALISED" else "INITIALISE SYSTEM",
                            color = Cyan, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                    }
                }
                Text("JARVIS ANDROID • FOUNDATION BUILD 0.1.0", color = Muted, fontSize = 9.sp,
                    letterSpacing = 1.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 5.dp))
            }
        }
    }
}

@Composable
private fun StatusPanel(label: String, value: String, active: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(10.dp)).padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Muted, fontSize = 10.sp, letterSpacing = 1.5.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(7.dp).background(if (active) Cyan else Muted.copy(alpha = 0.6f), CircleShape))
            Text(value, color = if (active) Cyan else Muted, fontSize = 10.sp, modifier = Modifier.padding(start = 8.dp), letterSpacing = 1.sp)
        }
    }
}
