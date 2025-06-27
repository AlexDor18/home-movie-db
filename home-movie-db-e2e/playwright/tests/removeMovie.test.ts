import test, { expect } from "@playwright/test";

test.describe("Test remove movie", () => {
    test('remove movie from collection', async ({page}) => {
        await page.goto('/search');
        await page.getByPlaceholder('Search...').fill('Die Verurteilten');
        await page.getByRole('button', { name: 'Search' }).click();

        // Warte bis die Suchergebnisse erscheinen
        await expect(page.getByRole('button', { name: 'Add Movie' }).first()).toBeVisible();

        await page.getByRole('button', { name: 'Add Movie' }).first().click();

        // Warte explizit auf das Checkmark-Icon
        const checkmark = page.locator('[data-testid="checkmark"]');
        await expect(checkmark).toBeVisible();

        await page.getByText('<- Zurück zur Startseite').click();
        await expect(page.getByRole('button', { name: 'Details' })).toBeVisible();
        await page.getByRole('button', { name: 'Details' }).click();
        await expect(page).toHaveURL(/.*\/movie\/\d+/);
        await expect(page.getByRole('button', { name: 'Entfernen' })).toBeVisible();
        await page.getByRole('button', { name: 'Entfernen' }).click();

        // Warte bis der "Hinzufügen"-Button wieder erscheint
        await expect(page.getByRole('button', { name: 'Hinzufügen' })).toBeVisible();
    });
});