# JARVIS Advanced — Android installation

## Install on Android
1. Put this folder on a static HTTPS host (for example, GitHub Pages or another static hosting service).
2. Open the hosted `index.html` in **Chrome on Android**.
3. Use Chrome's menu (⋮) → **Add to Home screen** or **Install app**.
4. Allow microphone access when prompted. Voice recognition availability depends on browser/device support.

## Included
- Voice input using the browser Speech Recognition API (where supported)
- Spoken replies using browser text-to-speech
- Time/date, arithmetic, weather lookup (Open-Meteo), local reminders/tasks
- Responsive sci-fi interface and basic offline app-shell caching

## Important limitations
This is a working starter assistant, not the full movie JARVIS and not a connected general-purpose AI. It does not control phone settings, messages, calls, or other apps. Full conversational AI requires a secure server-side connection to an AI provider; do not put private API keys in this front-end app. Weather needs internet. Browser speech recognition may also require internet and may not work in every browser.

## Files
- `index.html` — app
- `manifest.webmanifest` — install metadata
- `sw.js` — basic offline caching
- `icon.svg` — app icon
