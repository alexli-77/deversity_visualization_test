import numpy as np
import matplotlib.pyplot as plt

def getData():
    data = np.loadtxt("./Jaccard_.csv", dtype="str", delimiter=",")
    # data = np.loadtxt("./ira.data", dtype="str", delimiter=",")
    arr = data[:,:-1]
    label = data[:,-1]
    arr = np.float32(arr)
    return arr, label

def my_mds(D, dims):
    m = len(D)
    D = D ** 2
    t2 = np.sum(D, axis=1, keepdims=True) / m
    t3 = np.sum(D, axis=0, keepdims=True) / m
    t4 = np.sum(D) / m**2
    B =   - (D - t2 - t3 + t4) / 2
    eig_val, eig_vector  = np.linalg.eig(B)
    index_  = np.argsort(-eig_val)[:dims]
    simple_vector = eig_vector[:,index_]
    simple_val = eig_val[index_]
    reduced_vector = simple_vector *  simple_val ** 0.5
    return reduced_vector

def draw(data_2d, labels):
    unque_labs = np.unique(labels)
    unque_labs = unque_labs.tolist()
    colors = [plt.cm.Spectral(each)
              for each in np.linspace(0, 1,len(unque_labs))]
    nodes_color = [colors[unque_labs.index(i)] for i in labels]
    plt.scatter(data_2d[:,0], data_2d[:,1], c=nodes_color)

if __name__ == '__main__':

    arr, label = getData()
    my_mds(arr,2)
    # print(arr)
    # print(label)
    draw(arr, label)
    plt.show()
    plt.savefig("./MDS.png")