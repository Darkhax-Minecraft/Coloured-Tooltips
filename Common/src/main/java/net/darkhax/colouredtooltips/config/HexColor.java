package net.darkhax.colouredtooltips.config;

public class HexColor {

    private final String hex;
    private final int decimal;

    public HexColor(String hex, int decimal) {

        this.hex = hex;
        this.decimal = decimal;
    }

    public String getHex() {

        return this.hex;
    }

    @Override
    public String toString() {
        return "HexColor{" +
                "hex='" + hex + '\'' +
                ", decimal=" + decimal +
                '}';
    }

    public int getDecimal() {

        return this.decimal;
    }
}
