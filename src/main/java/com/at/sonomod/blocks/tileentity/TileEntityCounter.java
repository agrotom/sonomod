package com.at.sonomod.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.Packet;

public class TileEntityCounter extends TileEntity {

    private int count;

    public int getCount() {

        return this.count;
    }

    public void incrementCount() {

        this.count++;

        this.markDirty();
    }

    public void decrementCount() {

        this.count--;

        this.markDirty();
    }

    @Override
    public boolean canUpdate() {

        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {

        tagCompound.setInteger("count", this.count);

        super.writeToNBT(tagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {

        this.count = tagCompound.getInteger("count");

        super.readFromNBT(tagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound tagCompound = new NBTTagCompound();

        this.writeToNBT(tagCompound);

        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {

        NBTTagCompound tagCompound = packet.getNbtCompound();

        this.readFromNBT(tagCompound);
    }
}
