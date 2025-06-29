/// <reference types="vitest" />
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

// https://vite.dev/config/
export default defineConfig({
  test: {
    globals: true,
    environment: 'jsdom',
    reporters: ['default'],
    coverage: {
      provider: 'v8',
      reporter: ['text', 'json', 'html', 'lcov'],
      reportOnFailure: true,

    },
    setupFiles: ["./src/tests/setup.ts"],
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://172.17.0.1:8080',
        changeOrigin: true,
      },
      '/auth': {
        target: 'http://172.17.0.1:8080',
        changeOrigin: true,
      },
    },
  },

  plugins: [react(), tailwindcss()],
})
