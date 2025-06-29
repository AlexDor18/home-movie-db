import { test as setup } from '@playwright/test';
import path from 'path';
import fs from 'fs';

const userFile = path.join(__dirname, '../.auth/user.json');
const authFile = path.join(__dirname, '../.auth/auth.json');

setup('authenticate', async ({ page }) => {
    if (!fs.existsSync(userFile)) {
      await page.screenshot({ path: 'init.png' });
      await page.goto('/signup');
      await page.getByPlaceholder('Vorname').fill('Playwright');
      await page.getByPlaceholder('Nachname').fill('User');
      await page.getByPlaceholder('Username').fill('playwrightuser');
      await page.getByPlaceholder('Password').fill('password123');
      await page.getByRole('button', { name: 'Registrieren' }).click();
      await page.screenshot({ path: 'registered.png' });
      await page.waitForURL('/');

      // End of user creation steps.

      await page.context().storageState({ path: userFile });
  }

  // Perform authentication steps. Replace these actions with your own.
  await page.goto('/');
  await page.getByLabel('Username').fill('playwrightuser');
  await page.getByLabel('Password').fill('password123');
  await page.screenshot({ path: 'login.png' });
  await page.getByRole('button', { name: 'Login' }).click();
  // Wait until the page receives the cookies.
  //
  // Sometimes login flow sets cookies in the process of several redirects.
  // Wait for the final URL to ensure that the cookies are actually set.
  await page.waitForURL('/home');

  // End of authentication steps.

  await page.context().storageState({ path: authFile });
});