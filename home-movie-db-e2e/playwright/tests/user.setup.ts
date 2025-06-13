import { test as setup } from '@playwright/test';
import path from 'path';
import fs from 'fs';

const authFile = path.join(__dirname, '../.auth/user.json');

setup('authenticate', async ({ page }) => {
  if (fs.existsSync(authFile)) {
    console.log('Skipping user creation');
    return;
  }

  await page.goto('/signup');
  await page.getByPlaceholder('Vorname').fill('Playwright');
  await page.getByPlaceholder('Nachname').fill('User');
  await page.getByPlaceholder('Username').fill('playwrightuser');
  await page.getByPlaceholder('Password').fill('password123');
  await page.getByRole('button', { name: 'Registrieren' }).click();
  await page.waitForURL('/');

  // End of user creation steps.

  await page.context().storageState({ path: authFile });
});
