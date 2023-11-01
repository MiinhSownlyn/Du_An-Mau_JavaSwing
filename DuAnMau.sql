-- Tạo cơ sở dữ liệu EduSys
CREATE DATABASE EduSys;
GO

-- Sử dụng cơ sở dữ liệu EduSys
USE EduSys;
GO

-- Tạo bảng NhanVien
CREATE TABLE NhanVien
(
    MaNV NVARCHAR(50) NOT NULL,
    MatKhau NVARCHAR(50) NOT NULL,
    VaiTro BIT NOT NULL,
    PRIMARY KEY (MaNV)
);
GO

-- Tạo bảng ChuyenDe
CREATE TABLE ChuyenDe
(
    MaCD NCHAR(5) NOT NULL,
    TenCD NVARCHAR(50) NOT NULL,
    HocPhi FLOAT NOT NULL,
    ThoiLuong INT NOT NULL,
    Hinh NVARCHAR(50) NOT NULL,
    MoTa NVARCHAR(255) NOT NULL,
    PRIMARY KEY (MaCD)
);
GO

-- Tạo bảng KhoaHoc
CREATE TABLE KhoaHoc
(
    MaKH INT IDENTITY(1,1) NOT NULL,
    MaCD NCHAR(5) NOT NULL,
    HocPhi FLOAT NOT NULL,
    ThoiLuong INT NOT NULL,
    NgayKG DATE NOT NULL,
    GhiChu NVARCHAR(50) NULL,
    MaNV NVARCHAR(50) NOT NULL,
    NgayTao DATE NOT NULL,
    PRIMARY KEY (MaKH),
    FOREIGN KEY (MaCD) REFERENCES ChuyenDe (MaCD) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

-- Tạo bảng NguoiHoc
CREATE TABLE NguoiHoc
(
    MaNH NCHAR(7) NOT NULL,
    HoTen NVARCHAR(50) NOT NULL,
    NgaySinh DATE NOT NULL,
    DienThoai NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    GhiChu NVARCHAR(MAX) NULL,
    MaNV NVARCHAR(50) NOT NULL,
    NgayDK DATE NOT NULL,
    PRIMARY KEY (MaNH),
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

-- Tạo bảng HocVien
CREATE TABLE HocVien
(
    MaHV INT IDENTITY(1,1) NOT NULL,
    MaKH INT NOT NULL,
    MaNH NCHAR(7) NOT NULL,
    Diem FLOAT NOT NULL,
    PRIMARY KEY (MaHV),
    FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH) ON DELETE CASCADE,
    FOREIGN KEY (MaNH) REFERENCES NguoiHoc(MaNH) ON UPDATE CASCADE
)
GO

CREATE PROC [dbo].[sp_BangDiem](@MaKH INT)
AS BEGIN
	SELECT
		nh.MaNH,
		nh.HoTen,
		hv.Diem
	FROM HocVien hv
		JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH
	WHERE hv.MaKH = @MaKH
	ORDER BY hv.Diem DESC
END
GO

CREATE PROC [dbo].[sp_ThongKeDiem]
AS BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(MaHV) SoHV,
		MIN(Diem) ThapNhat,
		MAX(Diem) CaoNhat,
		AVG(Diem) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKH=hv.MaKH
		JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
	GROUP BY TenCD
END
GO

CREATE PROC [dbo].[sp_ThongKeDoanhThu](@Year INT)
AS BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(DISTINCT kh.MaKH) SoKH,
		COUNT(hv.MaHV) SoHV,
		SUM(kh.HocPhi) DoanhThu,
		MIN(kh.HocPhi) ThapNhat,
		MAX(kh.HocPhi) CaoNhat,
		AVG(kh.HocPhi) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKH=hv.MaKH
		JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
	WHERE YEAR(NgayKG) = @Year
	GROUP BY TenCD
END
GO

CREATE PROC [dbo].[sp_ThongKeNguoiHoc]
AS BEGIN
	SELECT
		YEAR(NgayDK) Nam,
		COUNT(*) SoLuong,
		MIN(NgayDK) DauTien,
		MAX(NgayDK) CuoiCung
	FROM NguoiHoc
	GROUP BY YEAR(NgayDK)
END
GO

-- Chuyen De
--SELECT MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa FROM     ChuyenDe WHERE  (MaCD = ?)
--UPDATE ChuyenDe
