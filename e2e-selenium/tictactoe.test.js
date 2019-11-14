describe('tictactoe', () => {

    const AppUrl = process.env['E2E_APP_URL'] || 'localhost:3000';

    beforeEach(async () => driver.get(AppUrl))

    it('should show 9 boxes', async() => {
        const elements = await driver.findElements(By.css('.box'));
        expect(elements.length).toBe(9);
    })

    it('should automatically turn', async () => {
        await driver.findElement(By.css('.box')).click();
        const userTurns = await driver.findElements(By.xpath(`//*[contains(@class,'box') and contains(text(),'1')]`))
        expect(userTurns.length).toBe(1);

    })

    afterEach(async () => cleanup());

})