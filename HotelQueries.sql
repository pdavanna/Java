#1. Can I identify all info needed to tell a customer their room ...?
#Even if the hotel had multiple floors? If it was a chain and had multiple hotels?
use Hotel;

SELECT Room.BranchID, Room.Floor, Room_Reservation.CheckInDate, Room_Reservation.CheckOutDate, CONCAT(Person.FirstName, ' ', Person.LastName) AS CustomerName, Room.RoomNumber
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Customer
ON Reservation.CustomerID = Customer.CustomerID
INNER JOIN Person
ON Customer.PersonID = Person.PersonID
WHERE Reservation.EndDate is NOT NULL;    
    
#2. Can promotions handle a 10% increase, or a 100$ discount?
#10. Are guests allowed to use multiple promotion codes? Per reservation? Per room?

SELECT Reservation.ReservationID, Bill.BillingDate, BillDetail.BillDetailName, PromotionCode.PromotionCodeName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
INNER JOIN PromotionCode_Reservation
ON Reservation.ReservationID = PromotionCode_Reservation.ReservationID
INNER JOIN PromotionCode
ON PromotionCode.PromotionCodeID = PromotionCode_Reservation.PromotionCodeID
GROUP BY BillDetail.Charge
Having BillDetail.Charge < 0;

#3. Can I pull a bill by reservation? By room? By customer? 
#How can I print a complete invoice?

#Look At This Again
SELECT Reservation.ReservationID, BillDetail.BillDetailName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
Where Room.RoomID = 1;

SELECT Room.RoomID, BillDetail.BillDetailName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
Where Room.RoomID = 1;

# Come Back to this
SELECT Reservation.CustomerID, BillDetail.BillDetailName, BillDetail.Charge
FROM Room
INNER JOIN Room_Reservation
ON Room.RoomID = Room_Reservation.RoomID
INNER JOIN Reservation
ON Room_Reservation.ReservationID = Reservation.ReservationID
INNER JOIN Bill
ON Reservation.ReservationID = Bill.ReservationID
INNER JOIN BillDetail
ON Bill.BillID = BillDetail.BillID
Where Reservation.CustomerID = 1;

#4.  If room #20 orders 3 bottles of champagne over 3 different days during their stay... 
#how does that appear on their bill?

SELECT rv.ReservationID, r.RoomNumber, rat.roomaddontypename, ra.dateordered 
#want to keep this as small as possible to keep data entry as small as possible -- if we can show these columns together, we can show them together in the context of a bill (detail)....
From Reservation rv
INNER JOIN Room_Reservation rr
ON rv.ReservationID = rr.ReservationID
Inner join Room r 
ON r.RoomID = rr.RoomID
INNER JOIN RoomAddOn ra
ON rr.Room_ReservationID = ra.Room_ReservationID
Inner join RoomAddOnType rat ON rat.RoomAddOnTypeID = ra.RoomAddOnTypeID;
    
#5. If I decide to do a rate hike on my room service, or stop offering valet...
#how does this effect my billing & old records?

    
#6. How are rooms priced? Base rate for all? By type? By bed size, or things in it?
#Or maybe even the location in the hotel? (Kingsized bed room vs pent house)?

SELECT RoomType.RoomTypeName, RoomRate.StartDate, RoomRate.EndDate, RoomRate.Rate
FROM RoomType
INNER JOIN RoomRate
ON RoomRate.RoomTypeID = RoomType.RoomTypeID;
    
#7. What happens if I cancel a reservation?

SELECT Customer.CustomerID, Reservation.ReservationID, RoomID, StartDate DATETIME, EndDate Datetime, Cancelled
From Customer
INNER JOIN Reservation
ON Customer.CustomerID = Reservation.CustomerID
INNER JOIN Room_Reservation
ON Reservation.ReservationID = Room_Reservation.ReservationID
WHERE Room_Reservation.Cancelled = 1;

#8. Can a wedding party have many rooms on the same reservations? 
#Do they all have to arrive and leave on the same day?

#Let's Understand this Before TMW
SELECT *
FROM room_reservation
JOIN
(
    SELECT Room_Reservation.ReservationID
	FROM Room_Reservation
	Group by ReservationID
	Having count(Room_Reservation.ReservationID) >= 2
) T2
ON room_reservation.ReservationID = T2.ReservationID;
    
#9. Can I track 2 HD tvs in a room, or other multiple amenities? Can the room TYPE change?

SELECT r.BranchID, r.RoomNumber, rt_ram.Quantity, ramt.RoomAmenityTypeName
FROM Room r
Inner join RoomType rt ON rt.RoomTypeID = r.RoomTypeID
INNER join RoomType_RoomAmenity rt_ram ON rt_ram.RoomTypeID = rt.RoomTypeID
INNER join RoomAmenity ram ON ram.RoomAmenityID = rt_ram.RoomAmenityID
Inner join RoomAmenityType ramt ON ramt.RoomAmenityTypeID = ram.RoomAmenityTypeID
WHERE rt_ram.Quantity > 1;


SELECT r.BranchID, r.RoomNumber, r.StartDate, IFNULL(rt.EndDate, 'Present') AS 'EndDate', rt.RoomTypeName
From Room r
inner join RoomType rt ON r.RoomTypeID = rt.RoomTypeID
ORDER BY r.RoomNumber;

#11. Can I waive the price of an amenity or addon? 
#(AKA can I offer a free bottle of champagne or a free king bed upgrade?)