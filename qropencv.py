import qrcode
import numpy
import cv2
'''
opencv_img=cv2.imread("1.png")
opencv_img2=cv2.imread("2.png")
opencv_img3=cv2.imread("3.png")
(x,y,z)=opencv_img.shape
hue0=[0,42,255]
hue1=[0,128,255]
hue2=[0,213,255]
hue3=[0,255,212]
hue4=[0,255,128]
hue5=[255,255,0]

for i in range(0,x):
	for j in range(0,y):
		if numpy.all(opencv_img[i,j]==hue0) and numpy.all(opencv_img2[i,j]==hue1):
			opencv_img2[i,j]=hue3
		elif numpy.all(opencv_img[i,j]==hue0):
			opencv_img2[i,j]=hue0
cv2.imwrite("psuedores.png",opencv_img2)
(x,y,z)=opencv_img2.shape
for i in range(0,x):
	for j in range(0,y):
		if numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img3[i,j]==hue2):
			opencv_img3[i,j]=hue4
		elif numpy.all(opencv_img2[i,j]==hue3) and numpy.all(opencv_img3[i,j]==hue2):
			opencv_img3[i,j]=[0,0,0]
		elif numpy.all(opencv_img2[i,j]==hue0) and numpy.all(opencv_img3[i,j]==hue2):
			opencv_img3[i,j]=hue5
		elif numpy.all(opencv_img2[i,j]==hue3):
			opencv_img3[i,j]=hue3
		elif numpy.all(opencv_img2[i,j]==hue1):
			opencv_img3[i,j]=hue1
		elif numpy.all(opencv_img2[i,j]==hue0):
			opencv_img3[i,j]=hue0
cv2.imwrite("res.png",opencv_img3)

'''
opencv_img1=cv2.imread("1.png")
opencv_img2=cv2.imread("2.png")
opencv_img3=cv2.imread("3.png")
opencv_img4=cv2.imread("4.png")
hue4=[0,42,255]
hue5=[0,128,255]
hue6=[0,170,255]
hue7=[0,213,255]
hue8=[0,255,212]
hue9=[0,255,170]
hue10=[0,255,128]
hue11=[0,255,43]
hue0=[170,255,0]
hue1=[255,255,0]
hue2=[255,170,0]

hue3=[255,0,128]
hue12=[255,0,255]
hue13=[170,0,255]
hue14=[149,0,255]
(x1,y1,z1)=opencv_img1.shape
#combining
for i in range(0,x1):
	for j in range(0,x1):
		if numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img3[i,j]==hue2) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=[0,0,0]
		elif numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img3[i,j]==hue2):
			opencv_img1[i,j]=hue10
 		elif numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue11
		elif numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img3[i,j]==hue2) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue12
		elif numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img3[i,j]==hue2) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue13
		elif numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img2[i,j]==hue1):
			opencv_img1[i,j]=hue6
		elif numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img3[i,j]==hue2):
			opencv_img1[i,j]=hue4
		elif numpy.all(opencv_img1[i,j]==hue0) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue5
		elif numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img3[i,j]==hue2):
			opencv_img1[i,j]=hue8
		elif numpy.all(opencv_img2[i,j]==hue1) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue9
		elif numpy.all(opencv_img3[i,j]==hue2) and numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue7
		elif numpy.all(opencv_img4[i,j]==hue3):
			opencv_img1[i,j]=hue3
		elif numpy.all(opencv_img2[i,j]==hue1):
			opencv_img1[i,j]=hue1
		elif numpy.all(opencv_img3[i,j]==hue2):
			opencv_img1[i,j]=hue2
print "res.png is created"
cv2.imwrite("res.png",opencv_img1)
