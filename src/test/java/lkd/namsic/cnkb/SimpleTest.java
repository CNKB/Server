package lkd.namsic.cnkb;

import lkd.namsic.cnkb.base.Location;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@SuppressWarnings("ConstantConditions")
class SimpleTest {

	@Test
	void equalTest() {
		Location location1 = Location.builder().x(1).y(2).fieldX(3).fieldY(4).build();
		Location location2 = Location.builder().x(1).build();
		Location location3 = null;

		System.out.println(location1.equalsField(location2));
		System.out.println(location1.equals(location3));
		System.out.println(location2.equals(location3));
		System.out.println(location3 instanceof Location);
	}

	@Test
	void ipTest() {
		String ipString = "192.168.200.102";
		long ip = Config.getInstance().ipToLong(ipString);

		System.out.println(ip);

		ipString = Config.getInstance().longToIp(ip);
		System.out.println(ipString);
	}

	@Test
	void locationBitTest() {
		Location location = Location.builder()
				.x(5)
				.y(4)
				.fieldX(60)
				.fieldY(25)
				.build();

		System.out.println("Location: " + location.getX() + "." + location.getY() +
				"." + location.getFieldX() + "." + location.getFieldY());

		int hexLocation = Location.fromLocation(location);
		System.out.println("HexLocation: " + hexLocation);

		location = Location.toLocation(hexLocation);
		System.out.println("Location: " + location.getX() + "." + location.getY() +
				"." + location.getFieldX() + "." + location.getFieldY());
	}

	@Test
	void dateTimeStringTest() {
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(Config.getInstance().formatter.format(dateTime));
	}

}