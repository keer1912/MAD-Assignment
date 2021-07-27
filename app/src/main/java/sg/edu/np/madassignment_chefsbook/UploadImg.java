package sg.edu.np.madassignment_chefsbook;

public class UploadImg {
    private String mImageUrl;
    private String mImageName;

    public UploadImg() {
        //
    }

    public UploadImg(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
